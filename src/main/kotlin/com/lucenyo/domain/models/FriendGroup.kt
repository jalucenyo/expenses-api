package com.lucenyo.domain.models

import java.util.UUID

data class FriendGroup(
  val id: UUID,
  val name: String,
  val friends: List<UUID>,
  val currency: String,
  val userId: String
)
