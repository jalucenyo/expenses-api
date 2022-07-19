package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateExpenseCommand
import com.lucenyo.domain.commands.UpdateExpenseCommand
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.usecases.ExpenseUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
  fun listExpenses(@PathVariable friendGroupId: UUID): Flux<Expense> {
    return expenseUseCase.fetchByFriendGroupId(friendGroupId);
  }

  @GetMapping("/{expenseId}")
  fun getExpense(@PathVariable expenseId: UUID): Mono<Expense>{
    return expenseUseCase.fetchById(expenseId);
  }

  @PostMapping
  fun create(@RequestBody command: CreateExpenseCommand): Mono<UUID> {
    return expenseUseCase.create(command)
  }

  @PutMapping("/{expenseId}")
  fun update(
    @PathVariable expenseId: UUID,
    @RequestBody command: UpdateExpenseCommand): Mono<Expense> {
    return expenseUseCase.update(expenseId, command)
  }

  @DeleteMapping("/{expenseId}")
  fun delete( @PathVariable expenseId: UUID): Mono<Void>{
    return expenseUseCase.delete(expenseId);
  }

}