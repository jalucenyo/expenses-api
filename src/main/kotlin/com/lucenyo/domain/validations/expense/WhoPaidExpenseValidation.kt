package com.lucenyo.domain.validations.expense

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.FriendRepository
import org.springframework.stereotype.Service

@Service
class WhoPaidExpenseValidation(
  val friendRepository: FriendRepository,
  ): ExpenseValidation{

  override suspend fun invoke(expense: Expense): Boolean {
    return friendRepository.findByIdAndUserId(expense.groupId, expense.userId)?.let { true } ?:
    throw NotFoundException(field = "WhoPaid")
  }

}