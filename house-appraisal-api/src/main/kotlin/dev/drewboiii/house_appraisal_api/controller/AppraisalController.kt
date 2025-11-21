package dev.drewboiii.house_appraisal_api.controller

import dev.drewboiii.house_appraisal_api.controller.dto.AppraisalResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.security.SecureRandom

@RestController
@RequestMapping("/appraisal")
class AppraisalController {

    @GetMapping("/flat")
    fun getFlatAppraisal(
        @RequestParam("address") address: String
    ): AppraisalResponseDto {
        Thread.sleep(10_000)
        return AppraisalResponseDto(
            address = address,
            price = BigDecimal(SecureRandom.getInstanceStrong().nextLong(100_000_000))
        )
    }

}