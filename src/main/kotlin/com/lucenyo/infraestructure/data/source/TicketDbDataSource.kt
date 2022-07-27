package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.TicketDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface TicketDbDataSource: ReactiveCrudRepository<TicketDocument, UUID> {

}
