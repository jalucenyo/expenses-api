package com.lucenyo.domain.usecases

import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.UUID

interface FetchExpensesByFriendGroupIdUseCase {
  operator fun invoke(friendGroupId: UUID): Flux<Expense>
}

@Service
class FetchExpensesByFriendGroupIdUseCaseImpl(
  val repository: ExpenseRepository
): FetchExpensesByFriendGroupIdUseCase {

  override fun invoke(friendGroupId: UUID): Flux<Expense> {
    return repository.findByFriendGroupId(friendGroupId)
  }

}