package com.lucenyo.infraestructure.data.source.mappers

import com.lucenyo.domain.models.Ticket
import com.lucenyo.infraestructure.data.source.documents.TicketDocument
import org.springframework.stereotype.Service

@Service
class TicketMapper {

  fun toDocument(domain: Ticket): TicketDocument {
    return TicketDocument(
      id = domain.id,
      expenseId = domain.expenseId
    )
  }

  fun toDomain(document: TicketDocument): Ticket {
    return Ticket(
      id = document.id,
      expenseId = document.expenseId,
      image = null
    )
  }

}