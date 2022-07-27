package com.lucenyo.domain.models

import org.springframework.http.codec.multipart.FilePart
import java.util.UUID

data class Ticket(
  val id: UUID,
  val expenseId: UUID,
  val image: FilePart?,
)
