package dev.drewboiii.house.service.api.controller.dto

import dev.drewboiii.house.service.api.repository.entity.HouseAppraisalEntity
import java.math.BigDecimal
import java.time.LocalDateTime

data class HouseAppraisalResponseDto(
    val id: String,
    val houseId: String,
    val userId: String,
    val price: BigDecimal,
    val createdAt: LocalDateTime,
)

fun HouseAppraisalEntity.toHouseAppraisalResponseDto() =
    HouseAppraisalResponseDto(
        id = this.id,
        houseId = this.houseId,
        userId = this.userId,
        price = this.price,
        createdAt = this.requestedAt
    )
