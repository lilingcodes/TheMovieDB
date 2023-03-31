package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lilingxu.themoviedb.ui.theme.Grey
import com.lilingxu.themoviedb.utils.IMAGE_BASE_URL

@Composable
fun SimpleImage(posterPath: String, modifier: Modifier = Modifier, shape: Shape =MaterialTheme.shapes.medium) {
    Card(
        modifier = modifier
            .height(140.dp)
            .width(96.dp),
        backgroundColor = Grey,
        shape = shape,
        elevation = 10.dp
    ) {
        AsyncImage(
            model = "$IMAGE_BASE_URL${posterPath}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}