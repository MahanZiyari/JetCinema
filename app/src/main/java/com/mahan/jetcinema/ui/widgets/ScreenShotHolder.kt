package com.mahan.jetcinema.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mahan.jetcinema.model.getMovies

@Composable
fun ScreenShot(imageUrl: String = getMovies()[2].images[1]) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable {  },
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp,
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = "ScreenShot",
            modifier = Modifier.fillMaxSize()
        )
    }
}



@Preview
@Composable
fun ScreenShotPreview() {
    ScreenShot()
}