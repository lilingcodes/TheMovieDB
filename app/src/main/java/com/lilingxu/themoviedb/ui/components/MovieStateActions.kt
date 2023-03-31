package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieStateActions(modifier: Modifier = Modifier) {
    val actions = listOf(
        Icons.Default.List,
        Icons.Default.Favorite,
        Icons.Default.Add,
        Icons.Default.Star,
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (item in actions) {
            Card(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                    },
                shape = MaterialTheme.shapes.large,
                //backgroundColor = DarkBlueLight
            ) {
                Icon(
                    modifier = Modifier.padding(12.dp),
                    imageVector = item,
                    contentDescription = null
                )
            }
        }
    }
}



