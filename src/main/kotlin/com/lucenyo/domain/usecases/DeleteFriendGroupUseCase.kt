package com.lucenyo.domain.usecases

import com.lucenyo.domain.repositories.FriendGroupRepository
import org.springframework.stereotype.Service
import java.util.UUID

interface DeleteFriendGroupUseCase {
  suspend operator fun invoke(id: UUID)
}

@Service
class DeleteFriendGroupUseCaseImpl(
  val repository: FriendGroupRepository
): DeleteFriendGroupUseCase {

  override suspend operator fun invoke(id: UUID) = repository.delete(id)

}
