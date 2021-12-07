package com.example.db

import io.micronaut.data.annotation.Where
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
@Where("deleted = false")
data class UserMicronautWhere(
        @Id
        val id: UUID,
        val email: String?,
        val deleted: Boolean
)


@Entity
@Table(name = "users")
data class UserWhereOnRepo(
        @Id
        val id: UUID,
        val email: String?,
        val deleted: Boolean
)
