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
    return dataSource.save(mapper.toEntity(friendGroup)).map(FriendGroupDocument::id)
  }

  override fun delete(id: UUID): Mono<Void> {
   return dataSource.deleteById(id)
  }

  override fun update(friendGroup: FriendGroup): Mono<FriendGroup> {
    return dataSource.save(mapper.toEntity(friendGroup)).map(mapper::toDomain)
  }

  override fun findById(id: UUID): Mono<FriendGroup> {
    return dataSource.findById(id).map(mapper::toDomain);
  }

  override fun findAll(): Flux<FriendGroup> {
    return dataSource.findAll().map(mapper::toDomain);
  }

}