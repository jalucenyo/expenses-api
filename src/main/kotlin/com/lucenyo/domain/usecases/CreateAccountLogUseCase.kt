package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateAccountLog
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.AccountLogRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.validations.accountlog.AccountLogValidation
import com.lucenyo.infraestructure.security.AuthenticationFacade
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.UUID

interface CreateAccountLogUseCase {

  suspend operator fun invoke(accountLogs: List<CreateAccountLog>): Flow<UUID>

}

@Service
class CreateAccountLogUseCaseImpl(
  val accountLogRepository: AccountLogRepository,
  val friendGroupRepository: FriendGroupRepository,
  val authentication: AuthenticationFacade,
  val validations: List<AccountLogValidation>
): CreateAccountLogUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override suspend fun invoke(accountLogs: List<CreateAccountLog>): Flow<UUID> {

    log.info("CreateAccountLogUseCase : {} ", accountLogs)

    val dateNow = OffsetDateTime.now()
    val auth = authentication.getAuth()

    val accountToCreate = accountLogs
      .map { mapToAccountLog(auth, it, fetchCurrencyGroup(it.groupId, auth.name), dateNow) }
      .toList()

    log.info("Validations: $validations")
    accountToCreate.forEach { accountLog ->  validations.forEach{ it(accountLog) }}

    return accountLogRepository.create(accountToCreate)

//    return authentication.getAuthentication()
//      .flatMapMany { auth -> Flux.fromIterable(accountLogs).map { Pair(auth, it) } }
//      .flatMap { (auth, it) -> Mono.zip(
//          Mono.just(auth),
//          Mono.just(it),
//          fetchCurrencyGroup(it.groupId, auth.name))
//      }
//      .map { (auth, it, currency) -> mapToAccountLog(auth, it, currency, dateNow) }
//      .filterWhen{ checkDebtor(it) }
//      .filterWhen{ checkCreditor(it) }
//      .filterWhen{ checkExpense(it) }
//      .filterWhen{ checkGroup(it) }
//      .collectList()
//      .log()
//      .flatMapMany { accountLogRepository.create(it) }

  }

  private suspend fun fetchCurrencyGroup(id: UUID, userId: String): String {
    return friendGroupRepository.findByIdAndUserId(id, userId)?.currency ?:
      throw NotFoundException(field = "currency")
  }

  private fun mapToAccountLog(
    auth: Authentication,
    it: CreateAccountLog,
    currency: String,
    date: OffsetDateTime,
  ) = AccountLog(
    id = UUID.randomUUID(),
    userId = auth.name,
    to = it.debtor,
    from = it.creditor,
    debit = it.debit,
    credit = it.credit,
    date = date,
    currency = currency,
    groupId = it.groupId,
    expenseId = it.expenseId
  )

}
