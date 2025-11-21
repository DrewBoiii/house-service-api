package dev.drewboiii.house.service.api.controller.dto

import dev.drewboiii.house.service.api.repository.entity.HouseEntity
import java.time.LocalDateTime
import java.util.*

data class HouseCreateDto(
    val userId: String,
    val address: String,
    val name: String? = null,
)

data class HouseResponseDto(
    val id: String,
    val userId: String,
    val name: String,
    val createdAt: LocalDateTime,
    val address: String? = null
)

fun HouseCreateDto.toHouseEntity() =
    HouseEntity(
        id = UUID.randomUUID().toString(),
        userId = this.userId,
        name = "Мой дом", //todo to config
        createdAt = LocalDateTime.now(), // todo clock for testing
        address = HouseEntity.Address(
            fullAddress = this.address, //todo
            country = this.address.split(",")[0].trim(),
            region = this.address.split(",")[1].trim(),
            city = this.address.split(",")[2].trim(),
            street = this.address.split(",")[3].trim(),
            houseNumber = this.address.split(",")[4].trim(),
            flat = this.address.split(",")[5].trim()
        )
    )

fun HouseEntity.toHouseResponseDto() =
    HouseResponseDto(
        id = this.id,
        userId = this.userId,
        name = this.name,
        createdAt = this.createdAt,
        address = this.address.fullAddress // todo
    )