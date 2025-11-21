package dev.drewboiii.house.service.api.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document
@CompoundIndexes(
    CompoundIndex(def = "{'houseId': 1, 'userId': 1}", unique = true)
)
data class HouseAppraisalEntity(
    @field:Id
    val id: String,
    val houseId: String,
    val userId: String,
    val price: BigDecimal,
    val requestedAt: LocalDateTime
)