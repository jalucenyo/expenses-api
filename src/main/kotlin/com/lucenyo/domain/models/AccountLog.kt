package com.lucenyo.domain.models

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class AccountLog(
  val id: UUID,
  val userId: String,
  val debtor: UUID,
  val creditor: UUID,
  val date: OffsetDateTime,
  val debit: BigDecimal = BigDecimal.ZERO,
  val credit: BigDecimal = BigDecimal.ZERO,
  val currency: String,
  val groupId: UUID,
  val expenseId: UUID,
)
