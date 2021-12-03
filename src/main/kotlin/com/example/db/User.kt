package com.example.db

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.Where

@Entity
@Table(name = "users")
@Where(clause = "deleted = false")
data class UserHibernateWhere(
        @Id
        val id: UUID,
        val email: String?,
        val deleted: Boolean

)

@Entity
@Table(name = "users")
@io.micronaut.data.annotation.Where("deleted = false")
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
