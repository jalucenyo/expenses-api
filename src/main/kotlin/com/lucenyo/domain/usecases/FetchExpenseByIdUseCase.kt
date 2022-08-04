package com.lucenyo.domain.usecases

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface FetchExpenseByIdUseCase {
  operator fun invoke(id: UUID): Mono<Expense>
}

@Service
class FetchExpenseByIdUseCaseImpl(
  val repository: ExpenseRepository,
): FetchExpenseByIdUseCase {

  override fun invoke(id: UUID): Mono<Expense> {
    return Mono.error(NotFoundException())
  }

}