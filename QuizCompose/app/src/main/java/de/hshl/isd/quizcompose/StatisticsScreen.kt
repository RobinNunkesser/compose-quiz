package de.hshl.isd.quizcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Statistics") })
        }) {
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
}
