package com.lucenyo.domain.validations.accountlog

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.ExpenseRepository
import org.springframework.stereotype.Service

@Service
class ExpenseAccountLogValidation(
  val expenseRepository: ExpenseRepository
): AccountLogValidation {

  override suspend fun invoke(accountLog: AccountLog): Boolean {
    return expenseRepository.findByIdAndUserId(accountLog.expenseId, accountLog.userId)?.let { true } ?:
    throw NotFoundException(field = "expense")
  }

}