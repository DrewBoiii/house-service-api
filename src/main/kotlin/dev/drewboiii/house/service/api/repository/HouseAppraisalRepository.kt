package dev.drewboiii.house.service.api.repository

import dev.drewboiii.house.service.api.repository.entity.HouseAppraisalEntity
import dev.drewboiii.house.service.api.repository.entity.HouseEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface HouseAppraisalRepository: CoroutineCrudRepository<HouseAppraisalEntity, String> {

    suspend fun findByHouseIdAndUserId(houseId: String, userId: String): HouseAppraisalEntity?

}