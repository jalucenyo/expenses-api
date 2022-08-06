package com.lucenyo.domain.models

import java.math.BigDecimal

data class BalanceResume (

    val totalReceivable: BigDecimal,
    val totalPayable: BigDecimal,
    val friends: List<BalanceFriend>
)

data class BalanceFriend (
    val name: String,
    val amount: BigDecimal
)