package com.example.db

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.toMono


@MicronautTest
class UserRepositoryTest {

    @Inject
    lateinit var userRepositoryWhereOnRepository: UserRepositoryWhereOnRepository


    @Inject
    lateinit var userRepositoryWithWhereOnEntity: UserRepositoryWithWhereOnEntity

    @Inject
    lateinit var userRepositoryNoWhere: UserRepositoryNoWhere

    @BeforeEach
    fun setUp() {
        userRepositoryNoWhere.deleteAll().toMono().block()
    }

    @Test
    fun `where on entity - update creates user`() {
        val user = UserWithWhere(UUID.randomUUID(), null, false)
        userRepositoryWithWhereOnEntity.update(user).toMono().block()
        val result = userRepositoryWithWhereOnEntity.findById(user.id).toMono().block()
        assert(result != null)
    }

    @Test
    fun `where on repository - update creates user`() {
        val user = User(UUID.randomUUID(), null, false)
        userRepositoryWhereOnRepository.update(user).toMono().block()
        val result = userRepositoryWhereOnRepository.findById(user.id).toMono().block()
        assert(result != null)
    }

    @Test
    fun `no where - update creates user`() {
        val user = UserNoWhere(UUID.randomUUID(), null, false)
        userRepositoryNoWhere.update(user).toMono().block()
        val result = userRepositoryNoWhere.findById(user.id).toMono().block()
        assert(result != null)
    }
}
