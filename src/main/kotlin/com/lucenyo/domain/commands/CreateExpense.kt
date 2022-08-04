package com.lucenyo.domain.commands

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class CreateExpense(
  val description: String,
  val amount: BigDecimal,
  val date: OffsetDateTime,
  val category: String,
  val whoPaid: UUID,
  val groupId: UUID,
)
