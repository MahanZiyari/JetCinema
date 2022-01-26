package com.mahan.jetcinema.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahan.jetcinema.ui.screens.DetailsScreen
import com.mahan.jetcinema.ui.screens.HomeScreen

@ExperimentalAnimationApi
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name,
    ) {
        // Here We Define Nodes or Screen for our Graph
        composable(route = MovieScreens.HomeScreen.name) {
            // Here we Pass where this should lead us to
            // In that case we are going to HomeScreen
            HomeScreen(navController = navController)
        }

        composable(
            route = MovieScreens.DetailsScreen.name + "/{movieId}",
            arguments = listOf(
                navArgument(
                    name = "movieId",
                    builder = { type = NavType.StringType })
            )
        ) {
            DetailsScreen(navController = navController, it.arguments?.getString("movieId"))
        }
    }
}