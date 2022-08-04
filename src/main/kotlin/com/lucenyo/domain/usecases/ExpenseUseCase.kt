package com.lucenyo.domain.usecases

import org.springframework.stereotype.Service

@Service
data class ExpenseUseCase(

  val create: CreateExpenseUseCase,
  val delete: DeleteExpenseUseCase,

  val fetchById: FetchExpenseByIdUseCase,
  val fetchByFriendGroupId: FetchExpensesByFriendGroupIdUseCase,

  )
