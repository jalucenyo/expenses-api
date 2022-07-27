package com.lucenyo.domain.models

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class Expense(
  val id: UUID,
  val description: String,
  val date: OffsetDateTime,
  val amount: BigDecimal,
  val currency: String,
  val category: String,
  val groupId: UUID,
  val friend: String,
  val userId: String
)
