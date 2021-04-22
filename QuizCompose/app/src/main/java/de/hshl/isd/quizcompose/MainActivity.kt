package de.hshl.isd.quizcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(viewModel = mainViewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavigationHost(navController, viewModel)
}
