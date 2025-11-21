package dev.drewboiii.house.service.api.client.dto

import dev.drewboiii.house.service.api.repository.entity.HouseAppraisalEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class HouseAppraisalWebClientResponseDto(
    val address: String,
    val price: BigDecimal,
)

fun HouseAppraisalWebClientResponseDto.toHouseAppraisalEntity(
    houseId: String,
    userId: String,
) =
    HouseAppraisalEntity(
        id = UUID.randomUUID().toString(),
        houseId = houseId,
        userId = userId,
        price = this.price,
        requestedAt = LocalDateTime.now(),
    )
