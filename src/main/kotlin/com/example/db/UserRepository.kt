package com.example.db

import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Repository
import io.micronaut.data.annotation.Where
import io.micronaut.data.annotation.repeatable.JoinSpecifications
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import java.util.UUID

@Repository
@Where("deleted = false")
interface UserRepositoryWithWhereOnRepository : ReactiveStreamsCrudRepository<UserWithWhereOnEntity, UUID>

@Repository
interface UserRepositoryWithWhereOnEntity : ReactiveStreamsCrudRepository<UserWithWhereOnRepository, UUID>

@Repository
interface UserRepositoryWithoutWhere : ReactiveStreamsCrudRepository<UserWithoutWhere, UUID>


@Repository
/*@JoinSpecifications(
    Join(value = "attachments", type = Join.Type.LEFT_FETCH),
    Join(value = "reactions", type = Join.Type.LEFT_FETCH),
    Join(value = "surveyChoiceSet", type = Join.Type.LEFT_FETCH),
)*/
@Where("post_deleted = false")
interface PostRepository : ReactiveStreamsCrudRepository<PostDTO, UUID>



