package com.lucenyo.domain.commands

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class CreateAccountLog(
  val debtor: UUID,
  val creditor: UUID,
  val debit: BigDecimal = BigDecimal.ZERO,
  val credit: BigDecimal = BigDecimal.ZERO,
  val groupId: UUID,
  val expenseId: UUID,
)
