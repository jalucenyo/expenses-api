package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateFriendGroup
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface CreateFriendGroupUseCase {
  operator fun invoke(createGroup: CreateFriendGroup): Mono<UUID>
}

@Service
class CreateFriendGroupUseCaseImpl(
  val repository: FriendGroupRepository,
  val authentication: AuthenticationFacade,
): CreateFriendGroupUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(createGroup: CreateFriendGroup): Mono<UUID> {

    return authentication.getAuthentication()
      .flatMap { auth -> repository.create(
        FriendGroup(
          id = UUID.randomUUID(),
          name = createGroup.name,
          friends = createGroup.friends,
          currency = createGroup.currency,
          userId = auth.name
        ))
      }
      .doOnSuccess{ log.info("Create friend group: {}", it)  }

  }

}
