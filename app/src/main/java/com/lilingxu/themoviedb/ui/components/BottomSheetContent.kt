package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.dateText
import com.lilingxu.themoviedb.ui.theme.DarkBlueLight

@Composable
fun BottomSheetContent(movie: Movie, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .heightIn(max = 350.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            MoviePreviewCard(movie) { onClick() }
            Divider(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
                color = Color.White
            )
            MoviePreviewActions()

        }
    }
}


@Composable
fun MoviePreviewCard(movie: Movie, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MovieImage(movie.poster_path)
        MovieDescription(movie)
    }
}

@Composable
private fun MovieDescription(movie: Movie, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6,
        )

        MovieStarRating(movie.vote_average, movie.vote_count)


        Text(
            text = dateText(movie.release_date),
            style = MaterialTheme.typography.overline,
        )

        Text(
            text = movie.overview,
            modifier = Modifier.height(100.dp),
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


@Composable
fun MoviePreviewActions(modifier: Modifier = Modifier) {
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
                backgroundColor = DarkBlueLight
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



