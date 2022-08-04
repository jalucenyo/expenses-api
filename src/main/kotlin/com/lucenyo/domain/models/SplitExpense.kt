package com.lucenyo.domain.models

import java.math.BigDecimal
import java.util.UUID

data class SplitExpense(
  val friendId: UUID,
  val amount: BigDecimal
)
