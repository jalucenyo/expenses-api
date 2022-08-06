package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.AccountLogDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface AccountLogDbDataSource: ReactiveCrudRepository<AccountLogDocument, UUID>
