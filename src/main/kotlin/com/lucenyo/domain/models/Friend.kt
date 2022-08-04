package com.lucenyo.domain.models

import java.util.UUID

data class Friend (
  val id: UUID,
  val userId: String,
  val name: String,
  val email: String,
  val phone: String?,
  val photo: String,
)