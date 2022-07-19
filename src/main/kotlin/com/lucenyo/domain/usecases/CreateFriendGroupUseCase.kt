package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateFriendGroupCommand
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface CreateFriendGroupUseCase {
  operator fun invoke(command: CreateFriendGroupCommand): Mono<UUID>
}

@Service
class CreateFriendGroupUseCaseImpl(
  val repository: FriendGroupRepository
): CreateFriendGroupUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(command: CreateFriendGroupCommand): Mono<UUID> {

    return repository.create(
      FriendGroup(
        id = UUID.randomUUID(),
        name = command.name,
        friends = command.friends
      )
    ).doOnSuccess{ log.info("Create friend group: {}", it)  }
  }

}
