package com.lucenyo.domain.usecases

import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import java.util.UUID

interface FetchExpensesByFriendGroupIdUseCase {
  suspend operator fun invoke(friendGroupId: UUID): Flow<Expense>
}

@Service
class FetchExpensesByFriendGroupIdUseCaseImpl(
  val repository: ExpenseRepository
): FetchExpensesByFriendGroupIdUseCase {

  override suspend fun invoke(friendGroupId: UUID): Flow<Expense> = repository.findByFriendGroupId(friendGroupId)

}