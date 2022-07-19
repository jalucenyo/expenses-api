package com.lucenyo.domain.commands

import java.math.BigDecimal
import java.time.OffsetDateTime

data class UpdateExpenseCommand(
  val description: String,
  val amount: BigDecimal,
  val currency: String,
  val category: String,
  val date: OffsetDateTime,
  val friendName: String
)
