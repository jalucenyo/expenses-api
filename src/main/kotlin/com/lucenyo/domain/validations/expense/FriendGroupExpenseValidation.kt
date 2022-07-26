package com.lucenyo.domain.validations.expense

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.springframework.stereotype.Service

@Service
class FriendGroupExpenseValidation(
  val friendGroupRepository: FriendGroupRepository,
  ): ExpenseValidation{

  override suspend fun invoke(expense: Expense): Boolean {
    return friendGroupRepository.findByIdAndUserId(expense.groupId, expense.userId)?.let{ true } ?:
    throw NotFoundException(field = "group")
  }

}