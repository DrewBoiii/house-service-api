package dev.drewboiii.house_appraisal_api.controller.dto

import java.math.BigDecimal

data class AppraisalResponseDto(
    val address: String,
    val price: BigDecimal,
)