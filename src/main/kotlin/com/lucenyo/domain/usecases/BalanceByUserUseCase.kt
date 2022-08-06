//package com.lucenyo.domain.usecases
//
//import com.lucenyo.domain.models.BalanceResume
//import com.lucenyo.domain.repositories.AccountLogRepository
//import com.lucenyo.infraestructure.security.AuthenticationFacade
//import reactor.core.publisher.Mono
//import reactor.kotlin.core.util.function.component1
//import reactor.kotlin.core.util.function.component2
//import reactor.util.function.Tuple2
//import java.math.BigDecimal
//import java.util.stream.Collectors
//
//interface BalanceByUserUseCase {
//
//  operator fun invoke(userId: String): Mono<BalanceResume>
//
//}
//
//class BalanceByUserUseCaseImpl(
//  val accountLogRepository: AccountLogRepository,
//  val authenticationFacade: AuthenticationFacade
//): BalanceByUserUseCase{
//
//  override fun invoke(userId: String): Mono<BalanceResume> {
//
//    authenticationFacade.getAuthentication()
//      .flatMapMany { auth -> accountLogRepository.findByUserId(auth.name) }
////      .filter { it.to.equals(userId) }
//      .groupBy { it.from }
//      .map { it }
//  }
//}
//
