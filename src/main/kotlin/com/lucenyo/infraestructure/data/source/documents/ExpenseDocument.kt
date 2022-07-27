package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Document("expenses")
data class ExpenseDocument(

  @Id
  val id: UUID,
  val description: String,
  val date: OffsetDateTime,
  val amount: BigDecimal,
  val currency: String,
  val category: String,
  val groupId: UUID,
  val friend: String,
  val userId: String,

)
