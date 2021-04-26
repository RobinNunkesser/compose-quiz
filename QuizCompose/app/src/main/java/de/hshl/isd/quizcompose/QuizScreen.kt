package de.hshl.isd.quizcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@ExperimentalAnimationApi
@Composable
fun QuizScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Quiz") },
                actions =
                {
                    Button(onClick = {
                        navController.navigate(Screen.Statistics.route)
                    }) {
                        Text("Statistics")
                    }
                }
            )
        })
    {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                    Text(
                        viewModel.question,
                        modifier = Modifier.padding(top = 32.dp),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedVisibility(visible = viewModel.showAnswer) {
                        Text(viewModel.answer)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(enabled = !viewModel.showAnswer,
                            onClick = {
                                viewModel.evaluateAnswer(false)
                            }) {
                            Text("Falsch")
                        }
                        Button(enabled = !viewModel.showAnswer,
                            onClick = {
                                viewModel.evaluateAnswer(true)
                            }) {
                            Text("Richtig")
                        }
                    }
                    Button(modifier = Modifier.padding(top = 8.dp),
                        enabled = !viewModel.showAnswer,
                        onClick = {
                            viewModel.skip()
                        }) {
                        Text("Überspringen")
                    }
                }
            }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun ComposablePreview() {
    QuizScreen(rememberNavController(), MainViewModel())
}