package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpenseCommand
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

interface CreateExpenseUseCase {
  operator fun invoke(command: CreateExpenseCommand): Mono<UUID>
}

@Service
class CreateExpenseUseCaseImpl(
  val expenseRepository: ExpenseRepository,
  val friendGroupRepository: FriendGroupRepository
): CreateExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(command: CreateExpenseCommand): Mono<UUID> {

    return friendGroupRepository.findById(command.friendGroupId)
      .switchIfEmpty(Mono.error(NotFoundException()))
      .map {
        Expense(
          id = UUID.randomUUID(),
          description = command.description,
          date = command.date,
          amount = command.amount,
          currency = command.currency,
          category = command.category,
          groupId = it.id,
          friend = command.friendName
        )
      }
      .doOnSuccess{ log.info("Create expense: {}", it)  }
      .flatMap(expenseRepository::create)
  }
}
