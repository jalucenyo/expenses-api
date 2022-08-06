package com.lucenyo.domain.validations.accountlog

import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.models.AccountLog
import com.lucenyo.domain.repositories.FriendRepository
import org.springframework.stereotype.Service

@Service
class CheckToAccountLogValidation(
  val friendRepository: FriendRepository,
): AccountLogValidation {

  override suspend fun invoke(accountLog: AccountLog): Boolean {
    return friendRepository.findByIdAndUserId(accountLog.to, accountLog.userId)?.let { true } ?:
    throw NotFoundException(field = "to")
  }

}