package de.hshl.isd.quizcompose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Quiz : Screen("quiz")
    object Statistics : Screen("statistics")
}

@ExperimentalAnimationApi
@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = Screen.Quiz.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Quiz.route) {
            QuizScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Statistics.route) {
            StatisticsScreen(viewModel = viewModel)
        }
    }
}