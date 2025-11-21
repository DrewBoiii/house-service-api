package dev.drewboiii.house.service.api.repository

import dev.drewboiii.house.service.api.repository.entity.HouseEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface HouseRepository: CoroutineCrudRepository<HouseEntity, String> {

    suspend fun findByIdAndUserId(id: String, userId: String): HouseEntity?

}