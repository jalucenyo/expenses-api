package com.lucenyo.domain.commands

data class CreateFriendGroupCommand(
  val name: String,
  val friends: List<String>
)
