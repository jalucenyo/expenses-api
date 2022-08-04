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
      groupId = domain.groupId,
      userId = domain.userId,
      whoPaid = domain.whoPaid,
    )
  }

  fun toDomain(document: ExpenseDocument): Expense {
    return Expense(
      id = document.id,
      description = document.description,
      date = document.date,
      amount = document.amount,
      groupId = document.groupId,
      userId = document.userId,
      whoPaid = document.whoPaid,
    )
  }

}