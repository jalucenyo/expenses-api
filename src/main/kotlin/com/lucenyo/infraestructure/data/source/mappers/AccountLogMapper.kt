package com.lucenyo.infraestructure.data.source.mappers

import com.lucenyo.domain.models.AccountLog
import com.lucenyo.infraestructure.data.source.documents.AccountLogDocument
import org.springframework.stereotype.Service

@Service
class AccountLogMapper {

  fun toDocument(domain: AccountLog): AccountLogDocument = AccountLogDocument(
    id = domain.id,
    userId = domain.userId,
    debtor = domain.debtor,
    creditor = domain.creditor,
    date = domain.date,
    debit = domain.debit,
    credit = domain.credit,
    currency = domain.currency,
    groupId = domain.groupId,
    expenseId = domain.expenseId
  )


}