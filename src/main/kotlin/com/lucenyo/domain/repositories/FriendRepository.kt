package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Friend
import reactor.core.publisher.Mono
import java.util.UUID

interface FriendRepository {

  fun create(friend: Friend): Mono<UUID>

  fun findByIdAndUserId(id: UUID, userId: String): Mono<Friend>

}