package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("friends_group")
data class FriendGroupDocument(

  @Id
  val id: UUID,
  val userId: String,
  val name: String,
  val currency: String,
  val friends: List<UUID>,

)