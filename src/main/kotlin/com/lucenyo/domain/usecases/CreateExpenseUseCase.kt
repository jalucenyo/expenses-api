package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpense
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

interface CreateExpenseUseCase {
  operator fun invoke(createExpense: CreateExpense): Mono<UUID>
}

@Service
class CreateExpenseUseCaseImpl(
  val expenseRepository: ExpenseRepository,
  val friendGroupRepository: FriendGroupRepository,
  val friendRepository: FriendRepository,
  val authentication: AuthenticationFacade,
  ): CreateExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(createExpense: CreateExpense): Mono<UUID> {

    log.info("CreateExpenseUseCase : {} ", createExpense)

    return authentication.getAuthentication()
      .filterWhen{ auth -> checkFriendGroupExist(createExpense.groupId, auth) }
      .filterWhen { auth -> checkWhoPaid(createExpense.whoPaid, auth) }
      .map { auth -> mapExpense(auth, createExpense) }
      .flatMap { expense -> expenseRepository.create(expense) }

  }

  private fun checkFriendGroupExist(groupId: UUID, auth: Authentication): Mono<Boolean>{
    return friendGroupRepository.findByIdAndUserId(groupId, auth.name).map { true }
      .switchIfEmpty(Mono.error(NotFoundException()))
  }

  private fun checkWhoPaid(friendId: UUID, auth: Authentication): Mono<Boolean> {
    return friendRepository.findByIdAndUserId(friendId, auth.name).map { true }
      .switchIfEmpty(Mono.error(NotFoundException()))
  }

  private fun mapExpense( auth: Authentication,
                          createExpense: CreateExpense) =
    Expense(
      id = UUID.randomUUID(),
      userId = auth.name,
      description = createExpense.description,
      date = createExpense.date,
      amount = createExpense.amount,
      groupId = createExpense.groupId,
      whoPaid = createExpense.whoPaid,
  )

}
