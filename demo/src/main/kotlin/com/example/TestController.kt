package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono

@Controller("/test")
class TestController {

    @Post("/post")
    fun test(): Mono<String> {
        return Mono.just("Hello World!")
    }
}
