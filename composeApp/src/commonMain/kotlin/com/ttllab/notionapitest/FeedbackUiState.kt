package com.ttllab.notionapitest

import com.ttllab.notionapitest.composeApp.BuildKonfig
import com.ttllab.notionapitest.feedback.Body
import com.ttllab.notionapitest.feedback.Content
import com.ttllab.notionapitest.feedback.Evaluation
import com.ttllab.notionapitest.feedback.Message
import com.ttllab.notionapitest.feedback.Parent
import com.ttllab.notionapitest.feedback.Properties
import com.ttllab.notionapitest.feedback.RichText
import com.ttllab.notionapitest.feedback.RichTextValue
import com.ttllab.notionapitest.feedback.Select
import com.ttllab.notionapitest.feedback.TitleText
import com.ttllab.notionapitest.feedback.Usability
import com.ttllab.notionapitest.feedback.UserId

data class FeedbackUiState(
    val userId: String = "",
    val evaluation: Usability = Usability.Good,
    val message: String = "",
)

fun FeedbackUiState.toBody(): Body {
    return Body(
        parent = Parent(database_id = BuildKonfig.NOTION_DB_ID),
        properties = Properties(
            user_id = UserId(
                title = listOf(
                    TitleText(
                        text = Content(
                            content = userId,
                        ),
                    ),
                ),
            ),
            evaluation = Evaluation(
                select = Select(
                    name = evaluation.name,
                ),
            ),
            message = Message(
                rich_text = listOf(
                    RichText(
                        text = RichTextValue(
                            content = message,
                            link = null,
                        ),
                        plain_text = message,
                        href = null,
                    ),
                ),
            ),
        ),
    )
}