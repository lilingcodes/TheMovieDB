package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R


@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.padding(horizontal = 50.dp),
        painter = painterResource(id = R.drawable.ic_logo_themoviedb),
        contentDescription = null
    )
}