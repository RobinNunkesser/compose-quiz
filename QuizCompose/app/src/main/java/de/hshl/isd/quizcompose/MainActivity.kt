package de.hshl.isd.quizcompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent(navigationViewModel = navigationViewModel)
            }
        }
    }
}


@Composable
private fun AppContent(navigationViewModel: NavigationViewModel,
                       mainViewModel: MainViewModel) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Quiz -> QuizScreen(mainViewModel)
                is Screen.Statistics -> StatisticsScreen(mainViewModel)
            }
        }
    }
}

