package com.lucenyo.infraestructure.data.source

import com.lucenyo.domain.models.AccountLog
import com.lucenyo.infraestructure.data.source.documents.AccountLogDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

interface AccountLogDbDataSource: ReactiveCrudRepository<AccountLogDocument, UUID>{


}