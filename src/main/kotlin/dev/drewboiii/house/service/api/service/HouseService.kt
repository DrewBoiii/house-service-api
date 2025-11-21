package dev.drewboiii.house.service.api.service

import dev.drewboiii.house.service.api.controller.dto.HouseCreateDto
import dev.drewboiii.house.service.api.controller.dto.HouseResponseDto
import dev.drewboiii.house.service.api.controller.dto.toHouseEntity
import dev.drewboiii.house.service.api.controller.dto.toHouseResponseDto
import dev.drewboiii.house.service.api.repository.HouseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KLogging
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class HouseService(
    val houseRepository: HouseRepository
) {

    suspend fun save(dto: HouseCreateDto) =
        try {
            houseRepository.save(dto.toHouseEntity())
        } catch (e: DuplicateKeyException) {
            logger.error(e) { "House for ${dto.userId} already exists" }
            throw RuntimeException("House for ${dto.userId} already exists") //todo
        }

    suspend fun getAll(): Flow<HouseResponseDto> =
        houseRepository.findAll().map { it.toHouseResponseDto() }

    suspend fun get(id: String, userId: String): HouseResponseDto =
        houseRepository.findByIdAndUserId(id, userId)?.toHouseResponseDto()
            ?: throw RuntimeException("House not found") // todo

    suspend fun get(id: String): HouseResponseDto =
        houseRepository.findById(id)?.toHouseResponseDto() ?: throw RuntimeException("House not found") // todo

    companion object: KLogging()
}