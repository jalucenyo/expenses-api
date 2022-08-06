package com.lucenyo.domain.exceptions

class PersistException(
  val code: String = "PERSIST_ERROR",
  override val message: String = "Error on persist document"
): Exception() {
}