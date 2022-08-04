package com.lucenyo.domain.exceptions

class PermissionException(
  val code: String = "AUTH",
  override val message: String = "User not permission"
): Exception() {
}