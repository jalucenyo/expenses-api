package com.lucenyo.infraestructure.security

import com.lucenyo.domain.exceptions.PermissionException
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationFacadeImpl: AuthenticationFacade {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override suspend fun getAuth(): Authentication {
    return ReactiveSecurityContextHolder.getContext().awaitFirstOrNull()?.authentication ?: throw PermissionException()
  }

  override fun getAuthentication(): Mono<Authentication> {
    return ReactiveSecurityContextHolder.getContext().map { it.authentication }
      .switchIfEmpty( Mono.error(PermissionException()))
      .doOnError { log.info("Error authentication, ${it.message}") }

  }

}