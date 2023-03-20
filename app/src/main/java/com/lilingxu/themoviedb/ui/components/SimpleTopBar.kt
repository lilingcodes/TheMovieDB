package com.lilingxu.themoviedb.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun SimpleTopBar(title: String = "", navigateOnClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateOnClick) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow back")
            }
        },
        title = {
            Text(title)
        }
    )
}