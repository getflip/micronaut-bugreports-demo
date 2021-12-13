package com.example.db

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.toMono
import java.time.Instant


@MicronautTest
class UserRepositoryTest {

    @Inject
    lateinit var userRepositoryWithoutWhere: UserRepositoryWithoutWhere

    @Inject
    lateinit var userRepositoryWithWhereOnRepository: UserRepositoryWithWhereOnRepository

    @Inject
    lateinit var userRepositoryWithWhereOnEntity: UserRepositoryWithWhereOnEntity

    @Inject
    lateinit var postRepository: PostRepository

    @BeforeEach
    fun setUp() {
        userRepositoryWithWhereOnRepository.deleteAll().toMono().block()
    }

    @Test
    fun `where on entity - update creates user`() {
        val user = UserWithWhereOnRepository(UUID.randomUUID(), null, false)
        userRepositoryWithWhereOnEntity.update(user).toMono().block()
        val result = userRepositoryWithWhereOnEntity.findById(user.id).toMono().block()
        assert(result != null)
    }

    @Test
    fun `where on repository - update creates user`() {
        val user = UserWithWhereOnEntity(UUID.randomUUID(), null, false)
        userRepositoryWithWhereOnRepository.update(user).toMono().block()
        val result = userRepositoryWithWhereOnRepository.findById(user.id).toMono().block()
        assert(result != null)
    }

    @Test
    fun `no where - update creates user`() {
        val user = UserWithoutWhere(UUID.randomUUID(), null, false)
        userRepositoryWithoutWhere.update(user).toMono().block()
        val result = userRepositoryWithoutWhere.findById(user.id).toMono().block()
        assert(result != null)
    }

    @Test
    fun `post test`() {
        val post = PostDTO(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "",
            "",
            null,
            UUID.randomUUID(),
            null,
            false,
            emptySet(),
            Instant.now(),
            null,
            emptySet(),
            emptySet(),
            null,
            false,
            null,
            null,
            null,
            null
        )
        postRepository.update(post).toMono().block()
        val result = postRepository.findById(post.id).toMono().block()
        assert(result != null)
    }
}
