package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.UpdateExpenseCommand
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

interface UpdateExpenseUseCase {
  operator fun invoke(id: UUID, command: UpdateExpenseCommand): Mono<Expense>
}

@Service
class UpdateExpenseUseCaseImpl(
  val expenseRepository: ExpenseRepository
): UpdateExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(id: UUID, command: UpdateExpenseCommand): Mono<Expense> {

    return expenseRepository.findById(id)
      .map {
        Expense(
          id = it.id,
          description = command.description,
          date = command.date,
          amount = command.amount,
          currency = command.currency,
          category = command.category,
          friend = command.friendName,
          groupId = it.groupId
        )
      }
      .flatMap(expenseRepository::update)

  }

}
