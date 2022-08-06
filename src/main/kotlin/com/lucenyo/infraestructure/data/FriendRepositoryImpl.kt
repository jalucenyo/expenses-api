package com.lucenyo.infraestructure.data

import com.lucenyo.domain.exceptions.PersistException
import com.lucenyo.domain.models.Friend
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.data.source.FriendDbDataSource
import com.lucenyo.infraestructure.data.source.mappers.FriendMapper
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendRepositoryImpl(
  private val dataSource: FriendDbDataSource,
  private val mapper: FriendMapper
): FriendRepository {

  override suspend fun create(friend: Friend): UUID =
    dataSource.save(mapper.toDocument(friend)).map { it.id }.awaitFirstOrNull() ?: throw PersistException()

  override suspend fun findByIdAndUserId(id: UUID, userId: String): Friend? =
    dataSource.findByIdAndUserId(id, userId).map(mapper::toDomain).awaitFirstOrNull()

}