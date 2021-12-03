package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID
import reactor.core.publisher.Mono

@Repository
interface UserRepositoryMicronautWhereOnEntity : ReactiveStreamsCrudRepository<UserMicronautWhere, UUID> {
    fun update(user: UserMicronautWhere): Mono<UserMicronautWhere>
    fun save(user: UserMicronautWhere): Mono<UserMicronautWhere>
}

@Repository
interface UserRepositoryHibernateWhereOnEntity : ReactiveStreamsCrudRepository<UserHibernateWhere, UUID> {
    fun update(user: UserHibernateWhere): Mono<UserHibernateWhere>
    fun save(user: UserHibernateWhere): Mono<UserHibernateWhere>
}
