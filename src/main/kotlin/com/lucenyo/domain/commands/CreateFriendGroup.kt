package com.lucenyo.domain.commands

import java.util.UUID

data class CreateFriendGroup(
  val name: String,
  val friends: List<UUID>,
  val currency: String,
)
