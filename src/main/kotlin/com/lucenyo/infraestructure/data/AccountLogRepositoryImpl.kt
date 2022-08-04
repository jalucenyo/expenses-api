package com.lucenyo.infraestructure.data

import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.AccountLogRepository
import com.lucenyo.infraestructure.data.source.AccountLogDbDataSource
import com.lucenyo.infraestructure.data.source.mappers.AccountLogMapper
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class AccountLogRepositoryImpl(
  val dataSource: AccountLogDbDataSource,
  val mapper: AccountLogMapper
): AccountLogRepository {

  override fun create(accountLogs: List<AccountLog>): Flux<UUID> {
    return dataSource.saveAll(accountLogs.map { mapper.toDocument(it) }).map { it.id }
  }

}
