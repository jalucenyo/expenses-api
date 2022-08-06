package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateExpense
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.usecases.ExpenseUseCase
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/api/v1/expenses")
class ExpenseRestController(
  val expenseUseCase: ExpenseUseCase
) {

  @GetMapping("/friendGroup/{friendGroupId}")
  suspend fun listExpenses(@PathVariable friendGroupId: UUID): Flow<Expense> {
    return expenseUseCase.fetchByFriendGroupId(friendGroupId);
  }

  @GetMapping("/{expenseId}")
  suspend fun getExpense(@PathVariable expenseId: UUID): Expense{
    return expenseUseCase.fetchById(expenseId);
  }

  @PostMapping
  suspend fun create(@RequestBody createExpense: CreateExpense): UUID {
    return expenseUseCase.create(createExpense)
  }

  @DeleteMapping("/{expenseId}")
  suspend fun delete(@PathVariable expenseId: UUID) {
    return expenseUseCase.delete(expenseId);
  }

}