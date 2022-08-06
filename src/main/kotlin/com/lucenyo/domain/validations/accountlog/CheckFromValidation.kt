package com.lucenyo.domain.validations.accountlog

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.FriendRepository
import org.springframework.stereotype.Service

@Service
class CheckFromValidation(
  val friendRepository: FriendRepository,
): AccountLogValidation {

  override suspend fun invoke(accountLog: AccountLog): Boolean {
    return friendRepository.findByIdAndUserId(accountLog.from, accountLog.userId)?.let { true } ?:
    throw NotFoundException(field = "from")
  }

}