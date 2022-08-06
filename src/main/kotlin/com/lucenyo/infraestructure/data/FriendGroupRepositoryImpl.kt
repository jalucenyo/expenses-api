package com.lucenyo.infraestructure.data

import com.lucenyo.domain.exceptions.PersistException
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.data.source.documents.FriendGroupDocument
import com.lucenyo.infraestructure.data.source.mappers.FriendGroupMapper
import com.lucenyo.infraestructure.data.source.FriendGroupDbDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendGroupRepositoryImpl(
  val dataSource: FriendGroupDbDataSource,
  val mapper: FriendGroupMapper
) : FriendGroupRepository{

  override suspend fun create(friendGroup: FriendGroup): UUID =
    dataSource.save(mapper.toDocument(friendGroup)).map(FriendGroupDocument::id).awaitFirstOrNull() ?:
      throw PersistException()

  override suspend fun delete(id: UUID) {
   dataSource.deleteById(id).awaitFirstOrNull() ?: throw PersistException()
  }

  override suspend fun update(friendGroup: FriendGroup): FriendGroup? {
    return dataSource.save(mapper.toDocument(friendGroup)).map(mapper::toDomain).awaitFirstOrNull() ?:
      throw PersistException()
  }

  override suspend fun findByIdAndUserId(id: UUID, userId: String): FriendGroup? {
    return dataSource.findByIdAndUserId(id, userId).map(mapper::toDomain).awaitFirstOrNull()
  }

  override suspend fun findByUserId(userId: String): Flow<FriendGroup> {
    return dataSource.findByUserId(userId).map(mapper::toDomain).asFlow()
  }

}