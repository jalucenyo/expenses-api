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
  fun create(@Valid @RequestBody createFriend: CreateFriend): Mono<UUID> {
    return friendUseCases.create(createFriend)
  }

//  @PutMapping("/{id}")
//  fun update(@PathVariable id: String): Mono<UUID> {
//
//  }
//
//  @DeleteMapping("/{id}")
//  fun delete(@PathVariable id: UUID): Mono<Void> {
//
//  }
//
//  @PutMapping("/{id}/photo")
//  fun addPhotoByFriendId(@PathVariable id: UUID): Mono<UUID>{
//
//  }
//
//  @GetMapping("{id}/photo")
//  fun getPhotoByFriendId(@PathVariable id: String): Mono<Void> {
//
//  }
//
//  @GetMapping
//  fun getFriendsByUserLogged(): Flux<Friend> {
//
//  }
//
//  @GetMapping
//  fun getFriendById(): Mono<Friend> {
//
//  }

}