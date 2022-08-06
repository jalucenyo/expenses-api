package com.lucenyo.infraestructure.data

import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.AccountLogRepository
import com.lucenyo.infraestructure.data.source.AccountLogDbDataSource
import com.lucenyo.infraestructure.data.source.mappers.AccountLogMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountLogRepositoryImpl(
  val dataSource: AccountLogDbDataSource,
  val mapper: AccountLogMapper
): AccountLogRepository {

  override suspend fun create(accountLogs: List<AccountLog>): Flow<UUID> {
    return dataSource.saveAll(accountLogs.map(mapper::toDocument)).asFlow().map { it.id }
  }

}
