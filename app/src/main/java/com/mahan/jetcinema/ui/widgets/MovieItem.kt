package com.mahan.jetcinema.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.mahan.jetcinema.model.Movie
import com.mahan.jetcinema.model.getMovies

@ExperimentalAnimationApi
@Composable
fun MovieItem(movie: Movie = getMovies().first(), onItemClick: (String) -> Unit) {
    // States
    var expanded by remember {
        mutableStateOf(false)
    }

    // State of iconButton rotation
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    // Ui
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(90.dp)
                    .weight(30f),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.images.first(),
                        builder = {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie Poster"
                )
            }

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(50f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Release: ${movie.year}", style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot: ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(movie.plot)
                                }
                            }
                        )
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption
                        )

                    }
                }
            }

            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .size(60.dp)
                    .weight(20f)
                    .rotate(rotation)
                    .padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Card Expansion Button",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(onItemClick = {})
}