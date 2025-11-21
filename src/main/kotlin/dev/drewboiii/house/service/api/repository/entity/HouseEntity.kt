package dev.drewboiii.house.service.api.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
@CompoundIndexes(
    CompoundIndex(def = "{'_id': 1, 'userId': 1}", unique = true)
)
data class HouseEntity(
    @field:Id
    val id: String,
    val userId: String,
    val name: String,
    val createdAt: LocalDateTime,
    val address: Address
) {
    data class Address(
        val fullAddress: String,
        val country: String,
        val region: String,
        val city: String,
        val street: String,
        val houseNumber: String,
        val flat: String
    )
}