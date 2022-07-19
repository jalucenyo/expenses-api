package com.lucenyo.infraestructure.data.source.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("friends_group")
data class FriendGroupDocument(

  @Id
  val id: UUID,
  val name: String,
  val friends: List<String>,

)