package com.lucenyo.domain.models

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class Expense(
  val id: UUID,
  val userId: String,

  val description: String,
  val date: OffsetDateTime,
  val amount: BigDecimal,

  val groupId: UUID,
  val whoPaid: UUID,
//  val splits: List<SplitExpense>

)
