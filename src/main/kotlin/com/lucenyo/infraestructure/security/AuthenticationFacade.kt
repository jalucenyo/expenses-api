package com.lucenyo.infraestructure.security

import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

interface AuthenticationFacade {
    suspend fun getAuth(): Authentication
    fun getAuthentication(): Mono<Authentication>
}