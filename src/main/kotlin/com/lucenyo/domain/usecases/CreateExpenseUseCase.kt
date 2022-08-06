package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateExpense
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.domain.validations.expense.ExpenseValidation
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

interface CreateExpenseUseCase {
  suspend operator fun invoke(createExpense: CreateExpense): UUID
}

@Service
class CreateExpenseUseCaseImpl(
  val expenseRepository: ExpenseRepository,
  val authentication: AuthenticationFacade,
  val validations: List<ExpenseValidation>,
  ): CreateExpenseUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override suspend operator fun invoke(createExpense: CreateExpense): UUID {

    log.info("CreateExpenseUseCase : {} ", createExpense)

    val auth = authentication.getAuth()
    val expense = mapExpense(auth, createExpense)

    log.info("Check validations, $validations")
    validations.forEach { it(expense) }

    return expenseRepository.create(expense)

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
