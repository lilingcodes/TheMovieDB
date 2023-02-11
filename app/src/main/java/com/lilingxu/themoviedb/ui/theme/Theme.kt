package com.lilingxu.themoviedb.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun TheMovieDBTheme(content: @Composable () -> Unit) {

    MaterialTheme(colors = ColorPalette, typography = Typography, content = content)
}
