package dev.drewboiii.house.service.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun houseAppraisalApiWebClient(): WebClient =
        WebClient.builder().baseUrl("http://localhost:8081").build() //todo config

}