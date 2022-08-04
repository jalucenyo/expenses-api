package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.AccountLog
import reactor.core.publisher.Flux
import java.util.UUID

interface AccountLogRepository {

  fun create(accountLogs: List<AccountLog>): Flux<UUID>

}
