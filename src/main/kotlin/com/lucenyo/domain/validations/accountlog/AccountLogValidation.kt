package com.lucenyo.domain.validations.accountlog

import com.lucenyo.domain.models.AccountLog

interface AccountLogValidation {

  suspend operator fun invoke(accountLog: AccountLog): Boolean

}