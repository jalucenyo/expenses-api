package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.ExpenseDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

interface ExpenseDbDataSource: ReactiveCrudRepository<ExpenseDocument, UUID>{

  fun findByGroupIdOrderByDateDesc(friendGroupId: UUID): Flux<ExpenseDocument>

}