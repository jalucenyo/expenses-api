package com.lucenyo.infraestructure.data.source.documents

import com.lucenyo.domain.models.ActivityType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Document("activities")
data class ActivityDocument(
  @Id
  val id: UUID,
  val userId: UUID,
  @DocumentReference
  val friend: FriendDocument,
  val date: OffsetDateTime,
  val amount: BigDecimal,
  val type: ActivityType,
)
