package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Ticket
import reactor.core.publisher.Mono
import java.util.UUID

interface TicketRepository {

  fun create(ticket: Ticket): Mono<UUID>

}