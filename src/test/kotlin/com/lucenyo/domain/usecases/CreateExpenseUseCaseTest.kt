package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpenseCommand
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

class CreateExpenseUseCaseTest {

  val expenseRepositoryMock: ExpenseRepository = mock()
  val friendGroupRepositoryMock: FriendGroupRepository = mock()

  //subject
  val createExpenseUseCase = CreateExpenseUseCaseImpl(expenseRepositoryMock, friendGroupRepositoryMock)

  @Test
  @DisplayName("should save expense and return id when friend group exist")
  fun test1(){

    //given
    val friendGroup = FriendGroup(
      id = UUID.randomUUID(),
      name = "testFriendGroup",
      friends = listOf("friend1", "friend2")
    )

    val command = CreateExpenseCommand(
      description = "anyDescription",
      amount = BigDecimal.valueOf(100.10),
      currency = "EUR",
      category = "coffee",
      date = OffsetDateTime.now(),
      friendName = "anyFriendName",
      friendGroupId = friendGroup.id
    )

    whenever(friendGroupRepositoryMock.findById(friendGroup.id)).thenReturn(Mono.just(friendGroup))
    whenever(expenseRepositoryMock.create(any())).thenReturn(Mono.just(UUID.randomUUID()))

    //when
    val result = createExpenseUseCase.invoke(command).block()

    // then
    assertNotNull(result)

    val argumentCaptor = argumentCaptor<Expense>()
    verify(expenseRepositoryMock).create(argumentCaptor.capture())
    assertEquals(argumentCaptor.firstValue.description, command.description)
    assertEquals(argumentCaptor.firstValue.amount, command.amount)
    assertEquals(argumentCaptor.firstValue.date, command.date)
    assertEquals(argumentCaptor.firstValue.friend, command.friendName)
    assertEquals(argumentCaptor.firstValue.groupId, command.friendGroupId)

  }

  @Test
  @DisplayName("should error when friend group not exist")
  fun test2(){

    //given
    val friendGroup = FriendGroup(
      id = UUID.randomUUID(),
      name = "testFriendGroup",
      friends = listOf("friend1", "friend2")
    )

    val command = CreateExpenseCommand(
      description = "anyDescription",
      amount = BigDecimal.valueOf(100.10),
      currency = "EUR",
      category = "coffee",
      date = OffsetDateTime.now(),
      friendName = "anyFriendName",
      friendGroupId = friendGroup.id
    )

    whenever(friendGroupRepositoryMock.findById(friendGroup.id)).thenReturn(Mono.empty())

    //when
    val result = createExpenseUseCase.invoke(command)

    // then
    StepVerifier
      .create(result)
      .expectErrorMatches { throwable: Throwable -> throwable is NotFoundException }
      .verify()

  }

}

//    StepVerifier.create(result).expectNextCount(1).verifyComplete()
//    every { friendGroupRepositoryMock.findById(friendGroup.id) } returns Mono.just(friendGroup)
//    val result = createExpenseUseCase.invoke(command).block()
//    var expenseSlot = slot<Expense>()
//    every {
//      expenseRepositoryMock.create(capture(expenseSlot))
//    } answers {
//      println("Hola")
//    }
//    verify (exactly = 1){ expenseRepositoryMock.create(capture(slot)) }
