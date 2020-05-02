package de.hshl.isd.quizcompose

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
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
                Text(viewModel.answer)
                Row {
                    Button(onClick = {
                        viewModel.evaluateAnswer(false)
                    }) {
                        Text("Falsch")
                    }
                    Button(onClick = {
                        viewModel.evaluateAnswer(true)
                    }) {
                        Text("Richtig")
                    }
                }
                Button(onClick = {
                    viewModel.increaseIndex()
                    viewModel.skippedQuestions++
                }) {
                    Text("Überspringen")
                }
            }

        }
    )
}
