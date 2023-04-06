package com.lilingxu.themoviedb.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun TheMovieDBTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        content = content,
        shapes = Shapes
    )

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = DarkBlue900
    )

}
