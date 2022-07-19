package com.lucenyo.infraestructure.data

import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.infraestructure.data.source.documents.ExpenseDocument
import com.lucenyo.infraestructure.data.source.mappers.ExpenseMapper
import com.lucenyo.infraestructure.data.source.ExpenseDbDataSource
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExpenseRepositoryImpl(
  val dataSource: ExpenseDbDataSource,
  val mapper: ExpenseMapper
) : ExpenseRepository{

  override fun create(expense: Expense): Mono<UUID> {
    return dataSource.save(mapper.toDocument(expense)).map(ExpenseDocument::id)
  }

  override fun delete(id: UUID): Mono<Void> {
   return dataSource.deleteById(id)
  }

  override fun update(expense: Expense): Mono<Expense> {
    return dataSource.save(mapper.toDocument(expense)).map(mapper::toDomain)
  }

  override fun findById(id: UUID): Mono<Expense> {
    return dataSource.findById(id).map(mapper::toDomain)
  }

  override fun findByFriendGroupId(friendGroupId: UUID): Flux<Expense> {
    return dataSource.findByGroupIdOrderByDateDesc(friendGroupId).map(mapper::toDomain)
  }

}