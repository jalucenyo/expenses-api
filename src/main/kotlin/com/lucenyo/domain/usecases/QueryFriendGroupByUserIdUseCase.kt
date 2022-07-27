package com.lucenyo.domain.usecases

import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

interface QueryFriendGroupByUserIdUseCase {
  operator fun invoke(): Flux<FriendGroup>
}

@Service
class QueryFriendGroupByUserIdUseCaseImpl(
  val friendGroupRepository: FriendGroupRepository,
  val authentication: AuthenticationFacade
): QueryFriendGroupByUserIdUseCase {

  override fun invoke(): Flux<FriendGroup> {
    return authentication.getAuthentication()
      .flatMapMany { auth -> friendGroupRepository.findByUserId(auth.name) }
  }

}
