package com.ttllab.notionapitest

import com.ttllab.notionapitest.composeApp.BuildKonfig
import com.ttllab.notionapitest.feedback.Body
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.utils.io.core.use
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object KtorClient {
    suspend fun sendFeedback(
        body: Body,
        afterAction: () -> Unit,
    ) {
        val response = HttpClient() {
            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    println("NotionResponse: ${clientException.response}")
                }
            }
        }.use { client ->
            client.post(
                urlString = "https://api.notion.com/v1/pages",
                block = {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${BuildKonfig.NOTION_API_KEY}")
                        append("Content-Type", "application/json")
                        append("Notion-Version", "2022-06-28")
                    }
                    setBody(Json.encodeToString(body))
                }
            )
        }

        if (response.status.isSuccess()) {
            afterAction()
        }
    }
}