package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.lilingxu.themoviedb.ui.components.MySpacer

@Composable
fun FavoriteScreen() {
    LazyColumn {
        item {


            val a = "What if you need several constructors in the same data class? "
            Text(text = "Favorite")
            Text(text = "h1 titulo TTITLE", style = MaterialTheme.typography.h1)
            Text(text = "h2 titulo TTITLE", style = MaterialTheme.typography.h2)
            Text(text = "h3 titulo TTITLE", style = MaterialTheme.typography.h3)
            Text(text = "h4 titulo TTITLE", style = MaterialTheme.typography.h4)
            Text(text = "h5 titulo TTITLE", style = MaterialTheme.typography.h5)
            Text(text = "h6 titulo TTITLE", style = MaterialTheme.typography.h6)
            Text(text = "subtitle1 $a", style = MaterialTheme.typography.subtitle1)
            Text(text = "subtitle2 $a", style = MaterialTheme.typography.subtitle2)
            Text(text = "body1 $a", style = MaterialTheme.typography.body1)
            Text(text = "body2 $a", style = MaterialTheme.typography.body2)
            Text(text = "overline $a", style = MaterialTheme.typography.overline)


            MySpacer()
            MySpacer()
            MySpacer()
            MySpacer()
            MySpacer()
            MySpacer()
            MySpacer()
        }
    }
}
