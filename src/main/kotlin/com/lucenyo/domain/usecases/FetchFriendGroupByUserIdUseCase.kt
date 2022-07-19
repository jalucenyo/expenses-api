package com.lucenyo.domain.usecases

import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.repositories.FriendGroupRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

interface FetchFriendGroupByUserIdUseCase {
  operator fun invoke(): Flux<FriendGroup>
}

@Service
class FetchFriendGroupByUserIdUseCaseImpl(
  val friendGroupRepository: FriendGroupRepository
): FetchFriendGroupByUserIdUseCase {

  override fun invoke(): Flux<FriendGroup> {
    return friendGroupRepository.findAll();
  }

}
