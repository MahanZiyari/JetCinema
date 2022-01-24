package com.mahan.jetcinema.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieNavigation () {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name,
    ) {
        // Here We Define Nodes or Screen for our Graph
        composable(route = MovieScreens.HomeScreen.name) {
            // Here we Pass where this should lead us to
            // In that case we are going to HomeScreen
        }
    }
}