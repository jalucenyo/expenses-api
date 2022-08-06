package com.lucenyo.domain.usecases

import com.lucenyo.domain.commands.CreateFriendGroup
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.infraestructure.security.AuthenticationFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

interface CreateFriendGroupUseCase {
  suspend operator fun invoke(createGroup: CreateFriendGroup): UUID
}

@Service
class CreateFriendGroupUseCaseImpl(
  val repository: FriendGroupRepository,
  val authentication: AuthenticationFacade,
): CreateFriendGroupUseCase {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override suspend operator fun invoke(createGroup: CreateFriendGroup): UUID {

    val auth = authentication.getAuth()

    return repository.create(
      FriendGroup(
        id = UUID.randomUUID(),
        name = createGroup.name,
        friends = createGroup.friends,
        currency = createGroup.currency,
        userId = auth.name
      ))

  }

}
