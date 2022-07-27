package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("tickets")
data class TicketDocument(
  @Id
  val id: UUID,
  val expenseId: UUID
)
