package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.annotation.Where
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID
import reactor.core.publisher.Mono

@Repository
@Where("deleted = false")
interface UserRepositoryMicronautWhereOnRepository : ReactiveStreamsCrudRepository<UserWhereOnRepo, UUID> {
    override fun findById(id: UUID): Mono<UserWhereOnRepo>
}
