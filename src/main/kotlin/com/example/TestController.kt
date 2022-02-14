package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono

@Controller("/")
class TestController {

    @Post("test")
    fun test(@Body body: Message): Mono<String> {
        return Mono.just(body.message)
    }
}

data class Message(val message: String)
