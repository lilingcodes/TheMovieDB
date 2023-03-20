package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    signOutOnPress: () -> Unit,
) {
    Column() {
        Text(text = "${sharedPref.getSessionId()}")
        Button(onClick = {
            sharedPref.clear()
            signOutOnPress()
        }) {
            Text(text = "Sign out")
        }
    }
}