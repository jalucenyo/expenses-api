package com.lucenyo.infraestructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

  @Bean
  @Throws(Exception::class)
  fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

    http
      .cors()
      .and().csrf().disable()
      .authorizeExchange{
        it
          .pathMatchers("/actuator/**").permitAll()
          .pathMatchers("/docs/**").permitAll()
          .anyExchange().authenticated()
      }.oauth2ResourceServer().jwt()

    return http.build()

  }

}