package dev.drewboiii.house.service.api.controller

import dev.drewboiii.house.service.api.controller.dto.HouseCreateDto
import dev.drewboiii.house.service.api.controller.dto.HouseResponseDto
import dev.drewboiii.house.service.api.service.HouseService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/houses")
class HouseController(
    private val houseService: HouseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun save(
        @RequestBody dto: HouseCreateDto
    ) = houseService.save(dto)

    @GetMapping
    suspend fun getByIdAndUserId(
        @RequestParam("id") id: String,
        @RequestParam("userId") userId: String
    ): HouseResponseDto = houseService.get(id, userId)

    @GetMapping("/all")
    suspend fun getAll(): Flow<HouseResponseDto> = houseService.getAll()

}