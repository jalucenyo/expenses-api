package com.lucenyo.infraestructure.security

import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

interface AuthenticationFacade {
  fun getAuthentication(): Mono<Authentication>
}