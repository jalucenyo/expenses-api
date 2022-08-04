package com.lucenyo.domain.commands

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateFriend (
  @NotBlank
  val name: String,
  @Email
  val email: String,
  @NotBlank
  val phone: String?,
)
