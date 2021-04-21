package de.hshl.isd.quizcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.hshl.isd.quizcompose.ui.theme.QuizComposeTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph(viewModel = mainViewModel)
        }
    }
}

