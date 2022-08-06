package com.lucenyo.domain.validations.expense

import com.lucenyo.domain.models.Expense

interface ExpenseValidation {

  suspend operator fun invoke(expense: Expense): Boolean

}