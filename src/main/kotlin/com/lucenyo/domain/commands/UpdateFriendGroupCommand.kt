package com.lucenyo.domain.commands


data class UpdateFriendGroupCommand(
  val name: String,
  val friends: List<String>
)
