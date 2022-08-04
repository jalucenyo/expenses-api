package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("friends")
data class FriendDocument (
  @Id
  val id: UUID,
  val userId: String,
  val name: String,
  val email: String,
  val phone: String?,
  val photo: String,
)