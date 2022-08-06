package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Friend
import reactor.core.publisher.Mono
import java.util.UUID

interface FriendRepository {

  suspend fun create(friend: Friend): UUID

  suspend fun findByIdAndUserId(id: UUID, userId: String): Friend?

}