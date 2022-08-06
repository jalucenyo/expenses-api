package com.lucenyo.application.rest

import com.lucenyo.domain.commands.CreateFriendGroup
import com.lucenyo.domain.models.FriendGroup
import com.lucenyo.domain.usecases.FriendGroupUseCases
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/friendsgroup")
class FriendGroupRestController(
  val friendGroupUseCases: FriendGroupUseCases
) {

  @GetMapping
  suspend fun listFriendGroups(): Flow<FriendGroup> {
    return friendGroupUseCases.fetchByUserId();
  }

  @PostMapping
  suspend fun create(@RequestBody command: CreateFriendGroup): UUID {
    return friendGroupUseCases.create(command)
  }

  @DeleteMapping("/{id}")
  suspend fun delete(@PathVariable  id: UUID) {
    return friendGroupUseCases.delete(id)
  }

}