package com.lucenyo.infraestructure.data

import com.lucenyo.domain.exceptions.PersistException
import com.lucenyo.domain.models.Expense
import com.lucenyo.domain.repositories.ExpenseRepository
import com.lucenyo.infraestructure.data.source.documents.ExpenseDocument
import com.lucenyo.infraestructure.data.source.mappers.ExpenseMapper
import com.lucenyo.infraestructure.data.source.ExpenseDbDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseRepositoryImpl(
  val dataSource: ExpenseDbDataSource,
  val mapper: ExpenseMapper
) : ExpenseRepository{

  override suspend fun create(expense: Expense): UUID {
    return dataSource.save(mapper.toDocument(expense)).map(ExpenseDocument::id).awaitFirstOrNull() ?:
      throw PersistException()
  }

  override suspend fun delete(id: UUID) {
   dataSource.deleteById(id).awaitFirstOrNull() ?: throw PersistException()
  }

  override suspend fun update(expense: Expense): Expense? {
    return dataSource.save(mapper.toDocument(expense)).map(mapper::toDomain).awaitFirstOrNull() ?:
      throw PersistException()
  }

  override suspend fun findByIdAndUserId(id: UUID, userId: String): Expense? {
    return dataSource.findByIdAndUserId(id, userId).map(mapper::toDomain).awaitFirstOrNull() ?:
      throw PersistException()
  }

  override suspend fun findByFriendGroupId(friendGroupId: UUID): Flow<Expense> {
    return dataSource.findByGroupIdOrderByDateDesc(friendGroupId).map(mapper::toDomain).asFlow()
  }

}