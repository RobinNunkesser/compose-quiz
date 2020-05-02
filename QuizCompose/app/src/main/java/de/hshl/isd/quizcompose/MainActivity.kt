package de.hshl.isd.quizcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

sealed class Screen {
    data class Quiz(val viewModel: MainViewModel) : Screen()
    data class Statistics(val viewModel: MainViewModel) : Screen()
}

@Model
object Status {
    var currentScreen: Screen = Screen.Quiz(MainViewModel())
}

@Composable
private fun AppContent() {
    Crossfade(Status.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Quiz -> QuizScreen(screen.viewModel)
                is Screen.Statistics -> StatisticsScreen(screen.viewModel)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        AppContent()
    }
}