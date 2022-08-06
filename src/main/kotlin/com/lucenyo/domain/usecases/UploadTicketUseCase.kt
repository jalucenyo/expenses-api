package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateTicket
import com.lucenyo.domain.exceptions.UploadErrorException
import com.lucenyo.domain.models.Ticket
import com.lucenyo.domain.repositories.TicketRepository
import org.slf4j.LoggerFactory
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface UploadTicketUseCase {

  suspend operator fun invoke(createTicket: CreateTicket, ticketImage: FilePart): UUID

}

@Service
class UploadTicketUseCaseImpl(
  val ticketRepository: TicketRepository
): UploadTicketUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override suspend operator fun invoke(createTicket: CreateTicket, ticketImage: FilePart): UUID {

    val ticket = Ticket(
      id = UUID.randomUUID(),
      expenseId = createTicket.expenseId,
      image = ticketImage
    )

    return ticketRepository.create(ticket) ?: throw UploadErrorException()

  }

}
