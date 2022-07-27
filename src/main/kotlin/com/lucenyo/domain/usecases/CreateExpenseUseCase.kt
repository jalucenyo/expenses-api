package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpenseCommand
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import java.util.*

interface CreateExpenseUseCase {
  operator fun invoke(command: CreateExpenseCommand): Mono<UUID>
}

@Service
class CreateExpenseUseCaseImpl(
  val expenseRepository: ExpenseRepository,
  val friendGroupRepository: FriendGroupRepository,
  val authentication: AuthenticationFacade,
  ): CreateExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(command: CreateExpenseCommand): Mono<UUID> {

    return authentication.getAuthentication()
      .zipWhen { auth -> friendGroupRepository.findByIdAndUserId(command.friendGroupId, auth.name) }
      .map {
        val (auth, friendGroup) = it
        Expense(
          id = UUID.randomUUID(),
          description = command.description,
          date = command.date,
          amount = command.amount,
          currency = command.currency,
          category = command.category,
          groupId = friendGroup.id,
          friend = command.friendName,
          userId = auth.name
        )
      }
      .switchIfEmpty(Mono.error(NotFoundException()))
      .doOnSuccess{ log.info("Create expense: {}", it)  }
      .flatMap(expenseRepository::create)

  }

}
