package dev.drewboiii.house.service.api.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    @Value("\${user-data.name}")
    private var username: String? = null

    @GetMapping
    suspend fun hello(@RequestParam(name = "name", required = false) name: String?) =
        name?.let { "Hello, $name!" } ?: "Hello, $username!"

}