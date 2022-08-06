package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateTicket
import com.lucenyo.domain.usecases.TicketUseCases
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/tickets")
class TicketRestController(
  val ticketUseCases: TicketUseCases
) {

  @PostMapping
  suspend fun create(@RequestPart command: CreateTicket,
                     @RequestPart ticketImage: FilePart): UUID {

    return ticketUseCases.upload(command, ticketImage)
  }

}