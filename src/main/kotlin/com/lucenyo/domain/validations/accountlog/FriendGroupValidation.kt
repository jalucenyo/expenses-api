package com.lucenyo.domain.validations.accountlog

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.FriendGroupRepository
import com.lucenyo.domain.repositories.FriendRepository
import org.springframework.stereotype.Service

@Service
class FriendGroupValidation(
  val friendGroupRepository: FriendGroupRepository
): AccountLogValidation {

  override suspend fun invoke(accountLog: AccountLog): Boolean {
    return friendGroupRepository.findByIdAndUserId(accountLog.groupId, accountLog.userId)?.let { true } ?:
    throw NotFoundException(field = "group")
  }

}