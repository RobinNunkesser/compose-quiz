package de.hshl.isd.quizcompose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.unit.dp

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
            Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.Center) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Bearbeitete Fragen")
                    Text(viewModel.answeredQuestions.toString())
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Richtig beantwortet")
                    Text(viewModel.correctAnswers.toString())
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Falsch beantwortet")
                    Text(viewModel.wrongAnswers.toString())
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Übersprungen")
                    Text(viewModel.skippedQuestions.toString())
                }
            }
        }
    )
}
