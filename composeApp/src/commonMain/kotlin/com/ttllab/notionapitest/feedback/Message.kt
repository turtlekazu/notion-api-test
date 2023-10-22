package com.ttllab.notionapitest.feedback

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val rich_text: List<RichText>,
)

@Serializable
data class RichText(
    val text: RichTextValue,
    val plain_text: String,
    val href: String?,
)

@Serializable
data class RichTextValue(
    val content: String,
    val link: String?,
)
