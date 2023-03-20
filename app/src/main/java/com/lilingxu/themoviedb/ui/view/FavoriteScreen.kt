package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lilingxu.themoviedb.ui.components.MySpacer

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
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

            Text(text = "primary", color = MaterialTheme.colors.primary)
            Text(text = "onPrimary", color = MaterialTheme.colors.onPrimary)
            Text(text = "primaryVariant", color = MaterialTheme.colors.primaryVariant)
            Text(text = "secondary", color = MaterialTheme.colors.secondary)
            Text(text = "onSecondary", color = MaterialTheme.colors.onSecondary)
            Text(text = "secondaryVariant", color = MaterialTheme.colors.secondaryVariant)
            Text(text = "surface", color = MaterialTheme.colors.surface)
            Text(text = "onSurface", color = MaterialTheme.colors.onSurface)
            Text(text = "primarySurface", color = MaterialTheme.colors.primarySurface)
            Text(text = "background", color = MaterialTheme.colors.background)
            Text(text = "onBackground", color = MaterialTheme.colors.onBackground)
            Text(text = "onError", color = MaterialTheme.colors.onError)


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
