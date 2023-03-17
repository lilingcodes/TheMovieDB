package com.lilingxu.themoviedb.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleAlertDialog(title: String, body: String, confirmOnClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = title)
        },
        text = {
            Text(body)
        },
        confirmButton = {
            Button(
                onClick =confirmOnClick
            ) {
                Text("Confirm")
            }
        },
    )
}