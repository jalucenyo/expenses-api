package com.lucenyo.domain.usecases

import org.springframework.stereotype.Service

@Service
data class AccountLogUseCases(
  val create: CreateAccountLogUseCase
)
