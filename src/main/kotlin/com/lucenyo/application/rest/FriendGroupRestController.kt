package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateFriendGroupCommand
import com.lucenyo.domain.commands.UpdateFriendGroupCommand
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.usecases.FriendGroupUseCases
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/api/v1/friendsgroup")
class FriendGroupRestController(
  val friendGroupUseCases: FriendGroupUseCases
) {

  @GetMapping
  fun listFriendGroups(): Flux<FriendGroup> {
    return friendGroupUseCases.fetchByUserId();
  }

  @PostMapping
  fun create(@RequestBody command: CreateFriendGroupCommand): Mono<UUID> {
    return friendGroupUseCases.create(command)
  }

  @PutMapping("/{id}")
  fun update(
    @PathVariable id: UUID,
    @RequestBody command: UpdateFriendGroupCommand): Mono<FriendGroup> {
    return friendGroupUseCases.update(id, command)
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable  id: UUID): Mono<Void> {
    return friendGroupUseCases.delete(id)
  }

}