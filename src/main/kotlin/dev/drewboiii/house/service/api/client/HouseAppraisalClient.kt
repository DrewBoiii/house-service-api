package dev.drewboiii.house.service.api.client

import dev.drewboiii.house.service.api.client.dto.HouseAppraisalWebClientResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class HouseAppraisalClient(
    private val houseAppraisalApiWebClient: WebClient
) {

    suspend fun getAppraisalFlat(address: String): HouseAppraisalWebClientResponseDto =
        houseAppraisalApiWebClient.get()
            .uri("/appraisal/flat?address={address}", address)
            .retrieve()
            .awaitBody<HouseAppraisalWebClientResponseDto>()
}