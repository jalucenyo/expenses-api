package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.AccountLog
import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux
import java.util.UUID

interface AccountLogRepository {

  suspend fun create(accountLogs: List<AccountLog>): Flow<UUID>

//  fun findByUserId(userId: String): Flux<AccountLog>

}
