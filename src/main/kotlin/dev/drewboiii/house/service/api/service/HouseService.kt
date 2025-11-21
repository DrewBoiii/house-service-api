package dev.drewboiii.house.service.api.service

import dev.drewboiii.house.service.api.client.HouseAppraisalClient
import dev.drewboiii.house.service.api.client.dto.toHouseAppraisalEntity
import dev.drewboiii.house.service.api.controller.dto.*
import dev.drewboiii.house.service.api.repository.HouseAppraisalRepository
import dev.drewboiii.house.service.api.repository.HouseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KLogging
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class HouseService(
    val houseRepository: HouseRepository,
    val houseAppraisalRepository: HouseAppraisalRepository,
    val houseAppraisalClient: HouseAppraisalClient,
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

    suspend fun getAppraisalData(houseId: String, userId: String): HouseAppraisalResponseDto {
        val house = houseRepository.findByIdAndUserId(houseId, userId)
            ?: throw RuntimeException("House not found")

        val address = house.address.fullAddress

        val houseAppraisal = houseAppraisalRepository.findByHouseIdAndUserId(houseId, userId)

        if (houseAppraisal != null) {
            return houseAppraisal.toHouseAppraisalResponseDto()
        }


        val appraisalFlat = houseAppraisalClient.getAppraisalFlat(address)

        return houseAppraisalRepository.save(appraisalFlat.toHouseAppraisalEntity(houseId, userId))
            .toHouseAppraisalResponseDto()
    }

    companion object : KLogging()
}