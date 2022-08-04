package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpense
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.exceptions.PermissionException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.models.Friend
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import com.lucenyo.infraestructure.security.AuthenticationFacadeImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*


@ExtendWith(SpringExtension::class)
class CreateExpenseUseCaseTest {

  @InjectMocks
  lateinit var createExpenseUseCase: CreateExpenseUseCaseImpl

  @Mock
  lateinit var expenseRepository: ExpenseRepository
  @Mock
  lateinit var friendGroupRepository: FriendGroupRepository
  @Mock
  lateinit var friendRepository: FriendRepository

  @Spy
  val authentication: AuthenticationFacade = AuthenticationFacadeImpl()


  @Test
  @WithMockUser(username = "userTest")
  fun `should create expense when data is correct`(){

    // when
    `when`(friendGroupRepository.findByIdAndUserId(any(), anyString())).thenReturn(Mono.just(generateFriendGroup()))
    `when`(friendRepository.findByIdAndUserId(any(), any())).thenReturn(Mono.just(generateFriend()))
    `when`(expenseRepository.create(any())).thenReturn(Mono.just(UUID.randomUUID()))

    val createExpense = CreateExpense(
      description = "test description",
      amount = BigDecimal.valueOf(100.10),
      date = OffsetDateTime.now(),
      category = "test category",
      whoPaid = UUID.randomUUID(),
      groupId = UUID.randomUUID(),
    )

    // given
    val result = createExpenseUseCase.invoke(createExpense)

    // then
    StepVerifier.create(result).consumeNextWith{ id -> assertNotNull(id) }.verifyComplete()

    // verify
    val captor = argumentCaptor<Expense>()
    verify(expenseRepository).create(captor.capture())

    val expenseSaved = captor.firstValue
    assertEquals("userTest", expenseSaved.userId)
    assertEquals(createExpense.amount, expenseSaved.amount)
    assertNotNull(expenseSaved.date)

  }

  @Test
  @WithMockUser(username = "userTest")
  fun `should return exception when friend group not exist`(){

    // when
    `when`(friendGroupRepository.findByIdAndUserId(any(), anyString())).thenReturn(Mono.empty())

    val createExpense = CreateExpense(
      description = "test description",
      amount = BigDecimal.valueOf(100.10),
      date = OffsetDateTime.now(),
      category = "test category",
      whoPaid = UUID.randomUUID(),
      groupId = UUID.randomUUID(),
    )

    // given
    val result = createExpenseUseCase.invoke(createExpense)

    // then
    StepVerifier.create(result)
      .expectErrorMatches { throwable -> throwable is NotFoundException }
      .verify()

  }

  @Test
  fun `should return exception when user not logged`(){

    // when
    val createExpense = CreateExpense(
      description = "test description",
      amount = BigDecimal.valueOf(100.10),
      date = OffsetDateTime.now(),
      category = "test category",
      whoPaid = UUID.randomUUID(),
      groupId = UUID.randomUUID(),
    )

    // given
    val result = createExpenseUseCase.invoke(createExpense)

    // then
    StepVerifier.create(result)
      .expectErrorMatches { throwable -> throwable is PermissionException }
      .verify()

  }

  private fun generateFriend() = Friend(
    id = UUID.randomUUID(),
    userId = "testUser",
    name = "Test Friend",
    email = "testfriend@local.local",
    phone = "666666666",
    photo = "test photo"
  )

  private fun generateFriendGroup() = FriendGroup(
    id = UUID.randomUUID(),
    name = "Test Group",
    friends = emptyList(),
    currency = "EUR",
    userId = "testUser"
  )

}
