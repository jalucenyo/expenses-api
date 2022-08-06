package com.lucenyo.domain.usecases

import com.lucenyo.domain.repositories.ExpenseRepository
import org.springframework.stereotype.Service
import java.util.UUID

interface DeleteExpenseUseCase {
  suspend operator fun invoke(id: UUID)
}

@Service
class DeleteExpenseUseCaseImpl(
  val repository: ExpenseRepository
): DeleteExpenseUseCase {

  override suspend operator fun invoke(id: UUID) =  repository.delete(id)

}