package com.example.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.annotation.Where
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Repository
@Where("deleted = false")
interface UserRepository : ReactiveStreamsCrudRepository<User, UUID>



@Repository
interface UserRepositoryWithWhereOnEntity : ReactiveStreamsCrudRepository<UserWithWhere, UUID>


