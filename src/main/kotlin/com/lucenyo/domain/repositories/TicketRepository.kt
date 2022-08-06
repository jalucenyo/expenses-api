package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Ticket
import reactor.core.publisher.Mono
import java.util.UUID

interface TicketRepository {

  suspend fun create(ticket: Ticket): UUID?

}