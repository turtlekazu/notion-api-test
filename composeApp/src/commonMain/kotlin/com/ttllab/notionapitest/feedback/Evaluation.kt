package com.ttllab.notionapitest.feedback

import kotlinx.serialization.Serializable

@Serializable
data class Evaluation(
    val select: Select,
)

@Serializable
data class Select(
    val name: String,
)

enum class Usability() {
    Good,
    Bad,
    Soso,
}