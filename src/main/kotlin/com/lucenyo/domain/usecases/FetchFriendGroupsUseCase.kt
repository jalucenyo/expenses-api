package com.lucenyo.domain.usecases

import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

interface QueryFriendGroupByUserIdUseCase {
  suspend operator fun invoke(): Flow<FriendGroup>
}

@Service
class QueryFriendGroupByUserIdUseCaseImpl(
  val friendGroupRepository: FriendGroupRepository,
  val authentication: AuthenticationFacade
): QueryFriendGroupByUserIdUseCase {

  override suspend fun invoke(): Flow<FriendGroup> = friendGroupRepository.findByUserId(authentication.getAuth().name)

}
