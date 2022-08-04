package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateAccountLog
import com.lucenyo.domain.usecases.AccountLogUseCases
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.UUID

@RestController
@RequestMapping("/api/v1/accountlogs")
class AccountLogController(
  val accountLogUseCases: AccountLogUseCases
) {

  @PostMapping
  fun create(@RequestBody accountLogs: List<CreateAccountLog>): Flux<UUID> {
    return accountLogUseCases.create(accountLogs)
  }

}