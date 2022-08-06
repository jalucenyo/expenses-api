package com.lucenyo.domain.usecases

import org.springframework.stereotype.Service

@Service
data class FriendGroupUseCases (

  val create: CreateFriendGroupUseCase,
  val delete: DeleteFriendGroupUseCase,

  val fetchByUserId: QueryFriendGroupByUserIdUseCase,

  )