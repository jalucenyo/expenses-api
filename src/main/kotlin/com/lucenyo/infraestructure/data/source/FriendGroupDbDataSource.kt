package com.lucenyo.infraestructure.data.source

import com.lucenyo.infraestructure.data.source.documents.FriendGroupDocument
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface FriendGroupDbDataSource: ReactiveCrudRepository<FriendGroupDocument, UUID>{

}