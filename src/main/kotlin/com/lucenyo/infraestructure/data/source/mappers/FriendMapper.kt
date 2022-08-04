package com.lucenyo.infraestructure.data.source.mappers

import com.lucenyo.domain.models.Friend
import com.lucenyo.infraestructure.data.source.documents.FriendDocument
import org.springframework.stereotype.Service

@Service
class FriendMapper {

  fun toDocument(domain: Friend) = FriendDocument(
    id = domain.id,
    userId = domain.userId,
    name = domain.name,
    email = domain.email,
    phone = domain.phone,
    photo = domain.photo
  )

  fun toDomain(document: FriendDocument) = Friend(
    id = document.id,
    userId = document.userId,
    name = document.name,
    email = document.email,
    phone = document.phone,
    photo = document.photo
  )

}