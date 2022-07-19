package com.lucenyo.domain.commands

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class CreateExpenseCommand(
  val description: String,
  val amount: BigDecimal,
  val currency: String,
  val category: String,
  val date: OffsetDateTime,
  val friendName: String,
  val friendGroupId: UUID
)
