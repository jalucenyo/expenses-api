package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.FriendGroup
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface FriendGroupRepository {

  fun create(friendGroup: FriendGroup): Mono<UUID>

  fun delete(id: UUID): Mono<Void>

  fun update(friendGroup: FriendGroup): Mono<FriendGroup>

  fun findByIdAndUserId(id: UUID, userId: String): Mono<FriendGroup>

  fun findByUserId(userId: String): Flux<FriendGroup>

}