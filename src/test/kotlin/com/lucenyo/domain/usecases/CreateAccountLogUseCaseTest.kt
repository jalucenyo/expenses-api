package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateAccountLog
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.models.Friend
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.AccountLogRepository
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import com.lucenyo.infraestructure.security.AuthenticationFacadeImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import uk.co.jemos.podam.api.PodamFactory
import uk.co.jemos.podam.api.PodamFactoryImpl
import java.math.BigDecimal
import java.util.*

@ExtendWith(SpringExtension::class)
class CreateAccountLogUseCaseTest {


  @InjectMocks
  lateinit var createAccountLogUseCase: CreateAccountLogUseCaseImpl

  @Mock
  lateinit var accountLogRepository: AccountLogRepository
  @Mock
  lateinit var friendRepository: FriendRepository
  @Mock
  lateinit var expenseRepository: ExpenseRepository
  @Mock
  lateinit var friendGroupRepository: FriendGroupRepository

  @Spy
  val authentication: AuthenticationFacade = AuthenticationFacadeImpl()

  val factory: PodamFactory = PodamFactoryImpl()

  @Test
  @WithMockUser(username = "userTest")
  fun `should create account logs when data is correct`(){

    // when
    val friendGroup = factory.manufacturePojo(FriendGroup::class.java)
    `when`(friendGroupRepository.findByIdAndUserId(any(), anyString())).thenReturn(Mono.just(friendGroup))

    val friend = factory.manufacturePojo(Friend::class.java)
    `when`(friendRepository.findByIdAndUserId(any(), anyString())).thenReturn(Mono.just(friend))

    val expense = factory.manufacturePojo(Expense::class.java)
    `when`(expenseRepository.findByIdAndUserId(any(), anyString())).thenReturn(Mono.just(expense))

    `when`(accountLogRepository.create(any())).thenReturn(Flux.just(UUID.randomUUID()))

    val createAccountLogs = listOf(
      CreateAccountLog(
        debtor = friend.id,
        creditor = friend.id,
        debit = BigDecimal.ZERO,
        credit = BigDecimal.valueOf(100.0),
        groupId = friendGroup.id,
        expenseId = expense.id
      )
    )

    // given
    val result = createAccountLogUseCase.invoke(createAccountLogs)

    // then
    StepVerifier.create(result).consumeNextWith{ id -> Assertions.assertNotNull(id) }.verifyComplete()

    // verify
    val captor = argumentCaptor<List<AccountLog>>()
    verify(accountLogRepository).create(captor.capture())

    val accountLogSaved = captor.firstValue
    assertEquals(1, accountLogSaved.size)
    assertEquals(friendGroup.currency, accountLogSaved[0].currency)
    assertEquals(friendGroup.id, accountLogSaved[0].groupId)
    assertEquals(friend.id, accountLogSaved[0].creditor)
    assertEquals(friend.id, accountLogSaved[0].debtor)
    assertEquals(expense.id, accountLogSaved[0].expenseId)

  }

}