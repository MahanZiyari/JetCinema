package com.mahan.jetcinema.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mahan.jetcinema.model.getMovies
import com.mahan.jetcinema.ui.widgets.MovieItem

@ExperimentalAnimationApi
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                title = { Text(text = "Jet Cinema") },
                elevation = 6.dp
            )
        },
    ) {
        // Scaffold Scope
        MainContent(navController = navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(navController: NavController) {
    LazyColumn(modifier = Modifier.padding(vertical = 1.dp, horizontal = 2.dp)) {
        items(getMovies()) {
            MovieItem(
                movie = it,
                onItemClick = {
                    //Todo: Navigate to Detail Screen
                }
            )
        }
    }
}