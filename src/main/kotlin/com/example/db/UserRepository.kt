package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.annotation.Where
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID

@Repository
@Where("deleted = false")
interface UserRepositoryWhereOnRepository : ReactiveStreamsCrudRepository<User, UUID>

@Repository
interface UserRepositoryWithWhereOnEntity : ReactiveStreamsCrudRepository<UserWithWhere, UUID>

@Repository
interface UserRepositoryNoWhere: ReactiveStreamsCrudRepository<UserNoWhere, UUID>


