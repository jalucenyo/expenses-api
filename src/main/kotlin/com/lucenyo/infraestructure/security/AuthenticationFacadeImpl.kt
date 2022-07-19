package com.lucenyo.infraestructure.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationFacadeImpl: AuthenticationFacade {

  override fun getAuthentication(): Mono<Authentication> {
    return ReactiveSecurityContextHolder.getContext().map { it.authentication }
  }

}