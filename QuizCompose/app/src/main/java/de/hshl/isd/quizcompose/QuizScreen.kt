package de.hshl.isd.quizcompose

import androidx.compose.animation.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Quiz") },
                actions =
                {
                    Button(onClick = {
                        Status.currentScreen = Screen.Statistics(viewModel)
                    }) {
                        Text("Statistics")
                    }
                }
            )
        },
        bodyContent = {
            Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    Text(
                        viewModel.question,
                        modifier = Modifier.padding(top = 32.dp),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(Modifier.fillMaxWidth()) {
                        val opacity = animateFloatAsState(
                            if (viewModel.showAnswer) 1f else 0f,
                            finishedListener = {
                                if (viewModel.showAnswer) {
                                    viewModel.increaseIndex()
                                    viewModel.showAnswer = false
                                }
                            }).value
                        Text(viewModel.answer, modifier = Modifier.drawOpacity(opacity))
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(enabled = !viewModel.showAnswer,
                            onClick = {
                                viewModel.evaluateAnswer(false)
                                viewModel.showAnswer = true
                            }) {
                            Text("Falsch")
                        }
                        Button(enabled = !viewModel.showAnswer,
                            onClick = {
                                viewModel.evaluateAnswer(true)
                                viewModel.showAnswer = true
                            }) {
                            Text("Richtig")
                        }
                    }
                    Button(modifier = Modifier.padding(top = 8.dp),
                        enabled = !viewModel.showAnswer,
                        onClick = {
                            viewModel.increaseIndex()
                            viewModel.skippedQuestions++
                        }) {
                        Text("Überspringen")
                    }
                }
            }
        }
    )
}