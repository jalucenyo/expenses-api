package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.Expense
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface ExpenseRepository {

  fun create(expense: Expense): Mono<UUID>

  fun delete(id: UUID): Mono<Void>

  fun update(expense: Expense): Mono<Expense>

  fun findByFriendGroupId(friendGroupId: UUID): Flux<Expense>

  fun findByIdAndUserId(id: UUID, userId: String): Mono<Expense>
}