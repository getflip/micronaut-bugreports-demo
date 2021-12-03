package com.example.db

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import java.util.UUID
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@MicronautTest
class UserRepositoryWhereOnEntityTest {

    @Inject
    lateinit var userRepositoryHibernateWhere: UserRepositoryHibernateWhereOnEntity

    @Inject
    lateinit var userRepositoryMicronautWhere: UserRepositoryMicronautWhereOnEntity

    @Test
    fun `update with hibernate @Where works`() {
        val user = UserHibernateWhere(UUID.randomUUID(), null, false)
        assertDoesNotThrow { userRepositoryHibernateWhere.update(user).toMono().block() }
    }

    @Test
    fun `update with micronaut @Where works`() {
        val user = UserMicronautWhere(UUID.randomUUID(), null, false)
        assertDoesNotThrow { userRepositoryMicronautWhere.update(user).toMono().block() }
    }

    @Test
    fun `hibernate @Where on entity - findAll does not return deleted results`() {
        val user = UserHibernateWhere(UUID.randomUUID(), null, true)
        userRepositoryHibernateWhere.save(user).toMono().block()

        val result = userRepositoryHibernateWhere.findAll().toFlux().collectList().block()
        assert(result.size == 0)
    }

    @Test
    fun `micronaut @Where on repository - findAll does not return deleted results`() {
        val user = UserMicronautWhere(UUID.randomUUID(), null, true)
        userRepositoryMicronautWhere.save(user).toMono().block()

        val result = userRepositoryMicronautWhere.findAll().toFlux().collectList().block()
        assert(result.size == 0)
    }

}
