package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.UpdateFriendGroup
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface UpdateFriendGroupUseCase {
  operator fun invoke(id: UUID, command: UpdateFriendGroup): Mono<FriendGroup>
}

@Service
class UpdateFriendGroupUseCaseImpl(
  val repository: FriendGroupRepository
): UpdateFriendGroupUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun invoke(id: UUID, command: UpdateFriendGroup): Mono<FriendGroup> {

    //TODO: Authentication
    return repository.findByIdAndUserId(id, "")
      .map {
        it.copy(
          name = command.name,
          friends = emptyList()
        )
      }
      .flatMap(repository::update)

  }

}
