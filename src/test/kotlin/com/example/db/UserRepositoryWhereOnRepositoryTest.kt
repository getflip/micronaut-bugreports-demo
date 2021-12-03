package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

// use to delete all
@Repository
interface UserRepository : ReactiveStreamsCrudRepository<UserWhereOnRepo, UUID>

@MicronautTest
class UserRepositoryWhereOnRepositoryTest {

    @Inject
    lateinit var userRepositoryHibernateWhere: UserRepositoryHibernateWhereOnRepository

    @Inject
    lateinit var userRepositoryMicronautWhere: UserRepositoryMicronautWhereOnRepository

    @Inject
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userRepository.deleteAll().toMono().block()
    }

    @Test
    fun `hibernate @Where on repository - findAll does not return deleted results`() {
        val user = UserWhereOnRepo(UUID.randomUUID(), null, true)
        userRepositoryHibernateWhere.save(user).toMono().block()

        val result = userRepositoryHibernateWhere.findAll().toFlux().collectList().block()
        assert(result.size == 0)
    }

    @Test
    fun `micronaut @Where on repository - findAll does not return deleted results`() {
        val user = UserWhereOnRepo(UUID.randomUUID(), null, true)
        userRepositoryMicronautWhere.save(user).toMono().block()

        val result = userRepositoryMicronautWhere.findAll().toFlux().collectList().block()
        assert(result.size == 0)
    }

    @Test
    fun `hibernate @Where on repository - findById does not return deleted result`() {
        val user = UserWhereOnRepo(UUID.randomUUID(), null, true)
        userRepositoryHibernateWhere.save(user).toMono().block()

        val result = userRepositoryHibernateWhere.findById(user.id).toMono().block()
        assert(result == null)
    }

    @Test
    fun `micronaut @Where on repository - findById does not return deleted result`() {
        val user = UserWhereOnRepo(UUID.randomUUID(), null, true)
        userRepositoryMicronautWhere.save(user).toMono().block()

        val result = userRepositoryMicronautWhere.findById(user.id).toMono().block()
        assert(result == null)
    }

}
