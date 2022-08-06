package com.lucenyo.domain.repositories

import com.lucenyo.domain.models.FriendGroup
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface FriendGroupRepository {

  suspend fun create(friendGroup: FriendGroup): UUID

  suspend fun delete(id: UUID)

  suspend fun update(friendGroup: FriendGroup): FriendGroup?

  suspend fun findByIdAndUserId(id: UUID, userId: String): FriendGroup?

  suspend fun findByUserId(userId: String): Flow<FriendGroup>

}