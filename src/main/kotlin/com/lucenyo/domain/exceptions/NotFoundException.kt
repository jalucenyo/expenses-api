package com.lucenyo.domain.exceptions

class NotFoundException(
  val code: String = "NOT_FOUND",
  override val message: String = "Not found exception"
): Exception() {
}