package com.lucenyo.infraestructure.data.source.mappers

import com.lucenyo.domain.models.Expense
import com.lucenyo.infraestructure.data.source.documents.ExpenseDocument
import org.springframework.stereotype.Service

@Service
class ExpenseMapper {

  fun toDocument(domain: Expense): ExpenseDocument {
    return ExpenseDocument(
      id = domain.id,
      description = domain.description,
      date = domain.date,
      amount = domain.amount,
      currency = domain.currency,
      category = domain.category,
      groupId = domain.groupId,
      friend = domain.friend
    )
  }

  fun toDomain(entity: ExpenseDocument): Expense {
    return Expense(
      id = entity.id,
      description = entity.description,
      date = entity.date,
      amount = entity.amount,
      currency = entity.currency,
      category = entity.category,
      groupId = entity.groupId,
      friend = entity.friend
    )
  }

}