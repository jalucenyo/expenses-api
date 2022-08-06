package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Expense
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ExpenseRepository {

  suspend fun create(expense: Expense): UUID

  suspend fun delete(id: UUID)

  suspend fun update(expense: Expense): Expense?

  suspend fun findByFriendGroupId(friendGroupId: UUID): Flow<Expense>

  suspend fun findByIdAndUserId(id: UUID, userId: String): Expense?

}