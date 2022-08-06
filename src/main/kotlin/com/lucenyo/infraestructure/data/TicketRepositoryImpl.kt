package com.lucenyo.infraestructure.data

import com.lucenyo.domain.exceptions.PersistException
import com.lucenyo.domain.models.Ticket
import com.lucenyo.domain.repositories.TicketRepository
import com.lucenyo.infraestructure.data.source.TicketDbDataSource
import com.lucenyo.infraestructure.data.source.TicketStoreSource
import com.lucenyo.infraestructure.data.source.mappers.TicketMapper
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*


@Service
class TicketRepositoryImpl(
  private val dataSource: TicketDbDataSource,
  private val storeSource: TicketStoreSource,
  private val mapper: TicketMapper
): TicketRepository {

  override suspend fun create(ticket: Ticket): UUID? {

    val metadata = HashMap<String, String>()
    metadata["Original-FileName"] = ticket.image!!.filename()

    storeSource.upload( fileName = ticket.id.toString(), metadata = metadata, file = ticket.image) ?:
      throw PersistException()
    return dataSource.save(mapper.toDocument(ticket)).map { it.id }.awaitFirstOrNull()

  }

}