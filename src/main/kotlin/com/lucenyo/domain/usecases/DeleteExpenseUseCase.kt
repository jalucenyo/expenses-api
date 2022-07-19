package com.lucenyo.domain.usecases

import com.lucenyo.domain.repositories.ExpenseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface DeleteExpenseUseCase {
  operator fun invoke(id: UUID): Mono<Void>
}

@Service
class DeleteExpenseUseCaseImpl(
  val repository: ExpenseRepository
): DeleteExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(id: UUID): Mono<Void> {
    return repository.delete(id)
  }

}