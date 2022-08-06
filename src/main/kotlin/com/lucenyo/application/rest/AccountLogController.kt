package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateAccountLog
import com.lucenyo.domain.usecases.AccountLogUseCases
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/accountlogs")
class AccountLogController(
  val accountLogUseCases: AccountLogUseCases
) {

  @PostMapping
  suspend fun create(@RequestBody accountLogs: List<CreateAccountLog>): Flow<UUID> {
    return accountLogUseCases.create(accountLogs)
  }

//  @GetMapping("/{userId}")
//  suspend fun balanceByUser(@PathVariable userId: String): BalanceResume {
//
//
//
//  }

}