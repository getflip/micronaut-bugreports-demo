package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.media.Schema

@Controller
class Controller {
    @Get
    fun test(): TestDTO = println(test.state)
}

data class TestDTO(
    @field:Schema(defaultValue = "false")
    val state: Boolean
)
