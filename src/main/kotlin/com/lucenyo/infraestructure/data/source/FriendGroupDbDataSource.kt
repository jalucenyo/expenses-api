package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.FriendGroupDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface FriendGroupDbDataSource: ReactiveCrudRepository<FriendGroupDocument, UUID>{

  fun findByIdAndUserId(id: UUID, userId: String): Mono<FriendGroupDocument>

  fun findByUserId(userId: String): Flux<FriendGroupDocument>

}