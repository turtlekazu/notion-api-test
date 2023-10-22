package com.ttllab.notionapitest.feedback

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val parent: Parent,
    val properties: Properties,
)

@Serializable
data class Parent(
    val database_id: String,
)

@Serializable
data class Properties(
    val user_id: UserId,
    val evaluation: Evaluation,
    val message: Message,
)