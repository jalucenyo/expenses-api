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
      friend = domain.friend,
      userId = domain.userId,
    )
  }

  fun toDomain(document: ExpenseDocument): Expense {
    return Expense(
      id = document.id,
      description = document.description,
      date = document.date,
      amount = document.amount,
      currency = document.currency,
      category = document.category,
      groupId = document.groupId,
      friend = document.friend,
      userId = document.userId,
    )
  }

}