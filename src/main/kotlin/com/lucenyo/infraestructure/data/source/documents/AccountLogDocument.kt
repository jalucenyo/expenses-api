package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Document("account-log")
data class AccountLogDocument(
  @Id
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
