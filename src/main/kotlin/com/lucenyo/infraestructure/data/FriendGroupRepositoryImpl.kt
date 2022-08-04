package com.lucenyo.infraestructure.data

import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.data.source.documents.FriendGroupDocument
import com.lucenyo.infraestructure.data.source.mappers.FriendGroupMapper
import com.lucenyo.infraestructure.data.source.FriendGroupDbDataSource
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class FriendGroupRepositoryImpl(
  val dataSource: FriendGroupDbDataSource,
  val mapper: FriendGroupMapper
) : FriendGroupRepository{

  override fun create(friendGroup: FriendGroup): Mono<UUID> {
    return dataSource.save(mapper.toDocument(friendGroup)).map(FriendGroupDocument::id)
  }

  override fun delete(id: UUID): Mono<Void> {
   return dataSource.deleteById(id)
  }

  override fun update(friendGroup: FriendGroup): Mono<FriendGroup> {
    return dataSource.save(mapper.toDocument(friendGroup)).map(mapper::toDomain)
  }

  override fun findByIdAndUserId(id: UUID, userId: String): Mono<FriendGroup> {
    return dataSource.findByIdAndUserId(id, userId).map(mapper::toDomain);
  }

  override fun findByUserId(userId: String): Flux<FriendGroup> {
    return dataSource.findByUserId(userId).map(mapper::toDomain);
  }

}