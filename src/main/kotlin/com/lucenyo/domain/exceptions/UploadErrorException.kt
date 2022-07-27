package com.lucenyo.domain.exceptions

class UploadErrorException(
  val code: String = "UPLOAD_ERROR",
  override val message: String = "Upload error."
): Exception() {
}