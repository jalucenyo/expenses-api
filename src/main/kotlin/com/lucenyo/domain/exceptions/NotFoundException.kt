package com.lucenyo.domain.exceptions

class NotFoundException(
  val code: String = "NOT_FOUND",
  val field: String? = null,
  override val message: String = "Not found exception"
): Exception() {
}