package de.hshl.isd.quizcompose

import androidx.compose.Composable
import androidx.ui.animation.animate
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar

@Composable
fun QuizScreen(viewModel: MainViewModel) {
    Scaffold(
        topAppBar = {
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
            Column {
                Text(viewModel.question)
                Box(Modifier.fillMaxWidth(), gravity = ContentGravity.Center) {
                    val opacity = animate(if (viewModel.showAnswer) 1f else 0f, endListener = {
                        if (viewModel.showAnswer) {
                            viewModel.increaseIndex()
                            viewModel.showAnswer = false
                        }
                    })
                    Text(viewModel.answer, modifier = Modifier.drawOpacity(opacity))
                }
                Row {
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
                Button(enabled = !viewModel.showAnswer,
                    onClick = {
                        viewModel.increaseIndex()
                        viewModel.skippedQuestions++
                    }) {
                    Text("Überspringen")
                }
            }

        }
    )
}

@Composable
fun VisibilityTransition(visibility: Boolean) {
    Box(Modifier.fillMaxWidth(), gravity = ContentGravity.Center) {
        val opacity = animate(if (visibility) 1f else 0f)
        Text("Visibility Transition", modifier = Modifier.drawOpacity(opacity))
    }
}
