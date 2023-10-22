package com.ttllab.notionapitest

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ttllab.notionapitest.feedback.Usability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun FeedbackInputScreen(
    modifier: Modifier = Modifier,
) {
    val uiState = remember { mutableStateOf(FeedbackUiState()) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Text(text = "Evaluation")

        Box(
            modifier = Modifier
                .clickable { isMenuExpanded = true }
                .border(0.8.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.small)
                .width(200.dp)
        ) {
            Text(
                text = uiState.value.evaluation.name,
                modifier = Modifier.padding(8.dp),
            )
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier.width(200.dp)
            ) {
                Usability.values().forEach { usability ->
                    DropdownMenuItem(
                        text = { Text(text = usability.name) },
                        onClick = {
                            uiState.value = uiState.value.copy(evaluation = usability)
                            isMenuExpanded = false
                        })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Message")
        OutlinedTextField(
            value = uiState.value.message,
            onValueChange = { uiState.value = uiState.value.copy(message = it) },
        )

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                KtorClient.sendFeedback(uiState.value.toBody(), afterAction = {
                    println("NotionResponse: sent successfully")
                    uiState.value = uiState.value.copy(message = "")
                })
            }
        }) {
            Text(text = "Send")
        }
    }
}