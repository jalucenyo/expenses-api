package com.lucenyo.application.rest.responses

data class ErrorResponse(
  val code: String,
  val field: String? = null,
  val message: String,
)
