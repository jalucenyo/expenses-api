package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateAccountLog
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.AccountLogRepository
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import reactor.kotlin.core.util.function.component3
import java.time.OffsetDateTime
import java.util.UUID

interface CreateAccountLogUseCase {

  operator fun invoke(accountLogs: List<CreateAccountLog>): Flux<UUID>

}

@Service
class CreateAccountLogUseCaseImpl(
  val accountLogRepository: AccountLogRepository,
  val friendRepository: FriendRepository,
  val expenseRepository: ExpenseRepository,
  val friendGroupRepository: FriendGroupRepository,
  val authentication: AuthenticationFacade
): CreateAccountLogUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(accountLogs: List<CreateAccountLog>): Flux<UUID> {

    log.info("CreateAccountLogUseCase : {} ", accountLogs)
    val dateNow = OffsetDateTime.now()

    return authentication.getAuthentication()
      .flatMapMany { auth -> Flux.fromIterable(accountLogs).map { Pair(auth, it) } }
      .flatMap { (auth, it) -> Mono.zip(
          Mono.just(auth),
          Mono.just(it),
          fetchCurrencyGroup(it.groupId, auth.name))
      }
      .map { (auth, it, currency) -> mapToAccountLog(auth, it, currency, dateNow) }
      .filterWhen{ checkDebtor(it) }
      .filterWhen{ checkCreditor(it) }
      .filterWhen{ checkExpense(it) }
      .filterWhen{ checkGroup(it) }
      .collectList()
      .log()
      .flatMapMany { accountLogRepository.create(it) }

  }

  private fun fetchCurrencyGroup(id: UUID, userId: String): Mono<String>{
    return friendGroupRepository.findByIdAndUserId(id, userId).map { it.currency }
      .switchIfEmpty { Mono.error(NotFoundException(field = "currency")) }
      .doOnError{ error -> log.error("Error fetch currency group, ${error.message}") }
  }

  private fun checkDebtor(accountLog: AccountLog) : Mono<Boolean> {
    return friendRepository.findByIdAndUserId(accountLog.debtor, accountLog.userId).map { true }
      .switchIfEmpty(Mono.error(NotFoundException(field = "debtor.")))
      .doOnError{ error -> log.error("Error check debtor, ${error.message}") }
  }

  private fun checkCreditor(accountLog: AccountLog) : Mono<Boolean> {
    return friendRepository.findByIdAndUserId(accountLog.creditor, accountLog.userId).map { true }
      .switchIfEmpty(Mono.error(NotFoundException(field = "creditor")))
      .doOnError{ error -> log.error("Error check creditor, ${error.message}") }
  }

  private fun checkExpense(accountLog: AccountLog): Mono<Boolean> {
    return expenseRepository.findByIdAndUserId(accountLog.expenseId, accountLog.userId).map { true }
      .switchIfEmpty(Mono.error(NotFoundException(field = "expense")))
      .doOnError{ error -> log.error("Error check expense, ${error.message}") }
  }

  private fun checkGroup(accountLog: AccountLog): Mono<Boolean> {
    return friendGroupRepository.findByIdAndUserId(accountLog.groupId, accountLog.userId).map { true }
      .switchIfEmpty(Mono.error(NotFoundException(field = "group")))
      .doOnError{ error ->  log.error("Error check group: ${error.message}") }
  }

  private fun mapToAccountLog(
    auth: Authentication,
    it: CreateAccountLog,
    currency: String,
    date: OffsetDateTime,
  ) = AccountLog(
    id = UUID.randomUUID(),
    userId = auth.name,
    debtor = it.debtor,
    creditor = it.creditor,
    debit = it.debit,
    credit = it.credit,
    date = date,
    currency = currency,
    groupId = it.groupId,
    expenseId = it.expenseId
  )

}
