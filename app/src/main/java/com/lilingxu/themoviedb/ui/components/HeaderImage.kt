package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.lilingxu.themoviedb.R


@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo_themoviedb),
        contentDescription = null
    )
}