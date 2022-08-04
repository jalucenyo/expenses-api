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

  operator fun invoke(command: CreateTicket, ticketImage: Mono<FilePart>): Mono<UUID>

}

@Service
class UploadTicketUseCaseImpl(
  val ticketRepository: TicketRepository
): UploadTicketUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(command: CreateTicket, ticketImage: Mono<FilePart>): Mono<UUID> {

    return ticketImage
      .map {
        Ticket(
          id = UUID.randomUUID(),
          expenseId = command.expenseId,
          image = it
        )
      }
      .flatMap { ticketRepository.create(it) }
      .doOnError { error ->
        log.error("Upload error: ", error)
        throw UploadErrorException()
      }
  }

}
