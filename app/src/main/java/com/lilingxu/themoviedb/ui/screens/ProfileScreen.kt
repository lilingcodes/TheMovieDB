package com.lilingxu.themoviedb.ui.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref

@Composable
fun ProfileScreen(
    signOutOnPress: () -> Unit,
) {

    Button(onClick = {
        sharedPref.clear()
        signOutOnPress()
    }) {
        Text(text = "Sign out")
    }
}