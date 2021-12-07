package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID
import reactor.core.publisher.Mono

@Repository
interface UserRepositoryMicronautWhereOnEntity : ReactiveStreamsCrudRepository<UserMicronautWhere, UUID> {
    override fun findById(postId: UUID): Mono<UserMicronautWhere>
}
