package com.lucenyo.infraestructure.data.source

import com.lucenyo.domain.models.Friend
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.data.source.mappers.FriendMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendRepositoryImpl(
  private val dataSource: FriendDbDataSource,
  private val mapper: FriendMapper
): FriendRepository {

  override fun create(friend: Friend) = dataSource.save(mapper.toDocument(friend)).map { it.id }

  override fun findByIdAndUserId(id: UUID, userId: String) = dataSource.findByIdAndUserId(id, userId)
    .map { mapper.toDomain(it) }

}