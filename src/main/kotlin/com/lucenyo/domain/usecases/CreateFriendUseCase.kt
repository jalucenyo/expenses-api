package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateFriend
import com.lucenyo.domain.models.Friend
import com.lucenyo.domain.repositories.FriendRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

interface CreateFriendUseCase {
  operator fun invoke(createFriend: CreateFriend): Mono<UUID>
}

@Service
class CreateFriendUseCaseImpl(
  val authentication: AuthenticationFacade,
  val friendRepository: FriendRepository
): CreateFriendUseCase {

  override fun invoke(createFriend: CreateFriend): Mono<UUID> {

    return authentication.getAuthentication()
      .flatMap { auth ->
        friendRepository.create(Friend(
          id = UUID.randomUUID(),
          userId = auth.name,
          name = createFriend.name,
          email = createFriend.email,
          phone = createFriend.phone,
          photo = "",
        ))
      }
  }

}
