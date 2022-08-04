package com.lucenyo.infraestructure.security

import com.lucenyo.domain.exceptions.PermissionException
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationFacadeImpl: AuthenticationFacade {

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun getAuthentication(): Mono<Authentication> {
    return ReactiveSecurityContextHolder.getContext().map { it.authentication }
      .switchIfEmpty( Mono.error(PermissionException()))
      .doOnError { log.info("Error authentication, ${it.message}") }

  }

}