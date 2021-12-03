package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID
import org.hibernate.annotations.Where
import reactor.core.publisher.Mono

@Repository
@io.micronaut.data.annotation.Where("deleted = false")
interface UserRepositoryMicronautWhereOnRepository : ReactiveStreamsCrudRepository<UserWhereOnRepo, UUID> {
    fun update(user: UserWhereOnRepo): Mono<UserWhereOnRepo>
    fun save(user: UserWhereOnRepo): Mono<UserWhereOnRepo>

    override fun findById(id: UUID): Mono<UserWhereOnRepo>

    fun findByEmailEquals(email: String): Mono<UserWhereOnRepo>
}

@Repository
@Where(clause = "deleted = false")
interface UserRepositoryHibernateWhereOnRepository : ReactiveStreamsCrudRepository<UserWhereOnRepo, UUID> {
    fun update(user: UserWhereOnRepo): Mono<UserWhereOnRepo>
    fun save(user: UserWhereOnRepo): Mono<UserWhereOnRepo>


    override fun findById(id: UUID): Mono<UserWhereOnRepo>

    fun findByEmailEquals(email: String): Mono<UserWhereOnRepo>
}
