package de.hshl.isd.quizcompose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val QUIZ_ROUTE = "quiz"
    const val STATISTICS_ROUTE = "statistics"
}

@ExperimentalAnimationApi
@Composable
fun NavGraph(startDestination: String = MainDestinations.QUIZ_ROUTE, viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.QUIZ_ROUTE) {
            QuizScreen(navController = navController, viewModel = viewModel)
        }
        composable(MainDestinations.STATISTICS_ROUTE) {
            StatisticsScreen(viewModel = viewModel)
        }
    }
}