package com.lucenyo.domain.commands


data class UpdateFriendGroup(
  val name: String,
  val friends: List<String>
)
