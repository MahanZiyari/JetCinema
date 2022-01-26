package com.mahan.jetcinema.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mahan.jetcinema.model.Movie
import com.mahan.jetcinema.model.getMovies
import com.mahan.jetcinema.ui.widgets.ScreenShot

@ExperimentalAnimationApi
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().filter { it.id == movieId }.first()
    Scaffold(
        modifier = Modifier,
        topBar = {
            DetailsScreenTopAppBar(movie = movie) {
                navController.popBackStack()
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(6.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = movie.images[0],
                            builder = { }),
                        contentDescription = "Movie Poster",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = movie.title, style = MaterialTheme.typography.h5)
                    Text(
                        text = "Director: ${movie.director}",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary
                    )
                }
            }

            // Rating and Year
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(20f)
                ) {
                    Text(text = "Rating", fontWeight = FontWeight.SemiBold)
                    Text(text = movie.rating)
                }

                Divider(
                    color = Color.DarkGray,
                    modifier = Modifier
                        .width(1.dp)
                        .height(25.dp)
                        .weight(0.5f)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(50f)
                ) {
                    Text(text = "Genre", fontWeight = FontWeight.SemiBold)
                    Text(text = movie.genre)
                }

                Divider(
                    color = Color.DarkGray,
                    modifier = Modifier
                        .width(1.dp)
                        .height(25.dp)
                        .weight(0.5f)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(20f)
                ) {
                    Text(text = "Release", fontWeight = FontWeight.SemiBold)
                    Text(text = movie.year)
                }
            }

            Button(
                onClick = {  },
                elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    contentColor = MaterialTheme.colors.onPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp, horizontal = 4.dp)
            ) {
                Text(
                    text = "Watch",
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onPrimary
                )
            }

            LazyRow(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 4.dp)
                    .fillMaxWidth()
            ) {
                items(movie.images) { imageUrl ->
                    ScreenShot(imageUrl = imageUrl)
                }
            }

            Description(movie = movie)
        }
    }
}


@Composable
fun DetailsScreenTopAppBar(movie: Movie, onNavigateBack: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = Modifier,
        elevation = 6.dp,
        title = { Text(text = movie.title) },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun Description(movie: Movie) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    Row (verticalAlignment = Alignment.Top) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(80f)
        ) {
            Text(
                text = "About this Movie",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )

            AnimatedVisibility(visible = expanded) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(text = movie.plot)
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                }
            }
        }

        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .size(40.dp)
                .weight(20f)
                .padding(end = 8.dp)
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "Card Expansion Button",
                tint = Color.DarkGray,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

