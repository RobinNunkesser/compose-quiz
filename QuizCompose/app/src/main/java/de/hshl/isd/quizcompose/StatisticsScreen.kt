package de.hshl.isd.quizcompose

import androidx.compose.Composable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack

@Composable
fun StatisticsScreen(viewModel: MainViewModel) {
    Scaffold(
        topAppBar = {
            TopAppBar(title = { Text("Statistics") },
                navigationIcon = {
                    IconButton(onClick = {
                        Status.currentScreen = Screen.Quiz(viewModel)
                    }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        bodyContent = {
            Column {
                Row {
                    Text("Bearbeitete Fragen")
                    Text(viewModel.answeredQuestions.toString())
                }
                Row {
                    Text("Richtig beantwortet")
                    Text(viewModel.correctAnswers.toString())
                }
                Row {
                    Text("Falsch beantwortet")
                    Text(viewModel.wrongAnswers.toString())
                }
                Row {
                    Text("Übersprungen")
                    Text(viewModel.skippedQuestions.toString())
                }
            }
        }
    )
}
