package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import java.time.Duration
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono


@Controller("/")
class Controller {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get
    fun testGet(): Mono<Long> {
        logger.info("Received request")
        return Mono.delay(Duration.ofMinutes(1))
            .doFinally { signal ->
                logger.info("Received signal: $signal")
            }
    }

    @Post("post")
    fun testPost(): Mono<Long> {
        logger.info("Received post request")
        return Mono.delay(Duration.ofMinutes(1))
            .doFinally { signal ->
                logger.info("Received signal: $signal")
            }
    }
}
