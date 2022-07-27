package com.lucenyo.infraestructure.data.source.mappers

import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.infraestructure.data.source.documents.FriendGroupDocument
import org.springframework.stereotype.Service

@Service
class FriendGroupMapper {

  fun toEntity(domain: FriendGroup): FriendGroupDocument {
    return FriendGroupDocument(
      id = domain.id,
      name = domain.name,
      friends = domain.friends,
      userId = domain.userId,
    )
  }

  fun toDomain(document: FriendGroupDocument): FriendGroup {
    return FriendGroup(
      id = document.id,
      name = document.name,
      friends = document.friends,
      userId = document.userId
    )
  }

}