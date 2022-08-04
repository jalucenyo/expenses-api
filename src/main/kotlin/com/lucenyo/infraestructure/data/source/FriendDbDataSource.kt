package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.FriendDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.*

interface FriendDbDataSource: ReactiveCrudRepository<FriendDocument, UUID> {

  fun findByIdAndUserId(id: UUID, userId: String): Mono<FriendDocument>

}
