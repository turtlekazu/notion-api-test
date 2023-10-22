package com.ttllab.notionapitest.feedback

import kotlinx.serialization.Serializable

@Serializable
data class UserId(
    val title: List<TitleText>,
)

@Serializable
data class TitleText(
    val text: Content,
)

@Serializable
data class Content(
    val content: String,
)