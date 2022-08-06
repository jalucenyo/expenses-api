package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateFriend
import com.lucenyo.domain.usecases.FriendUseCases
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/friends")
class FriendRestController(
  private val friendUseCases: FriendUseCases
) {

  @PostMapping
  suspend fun create(@Valid @RequestBody createFriend: CreateFriend): UUID {
    return friendUseCases.create(createFriend)
  }

}