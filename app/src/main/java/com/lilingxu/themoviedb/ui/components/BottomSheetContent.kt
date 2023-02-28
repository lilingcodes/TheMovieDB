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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.theme.DarkBlueLight
import com.lilingxu.themoviedb.ui.theme.Grey
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme
import com.lilingxu.themoviedb.utils.IMAGE_BASE_URL

@Composable
fun BottomSheetContent(movie: Movie) {
    Surface(
        modifier = Modifier
            .heightIn(min = 350.dp, max = 350.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            MoviePreviewCard(movie)
            Divider(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
                color = Color.White
            )
            MoviePreviewActions()

        }
    }
}


@Composable
fun MoviePreviewCard(movie: Movie) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(
            modifier = Modifier
                .height(140.dp)
                .width(96.dp),
            backgroundColor = Grey,
            shape = MaterialTheme.shapes.medium,
            elevation = 10.dp
        ) {
            AsyncImage(
                model = "$IMAGE_BASE_URL${movie.poster_path}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
            )

            MovieStarRating(movie.vote_average, movie.vote_count)

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "2016-08-03",
                    style = MaterialTheme.typography.overline,
                )
            }
            Text(
                text = movie.overview,
                modifier = Modifier.height(100.dp),
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
            )
        }

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
        horizontalArrangement = Arrangement.SpaceEvenly
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

@Preview
@Composable
fun Preview() {
    TheMovieDBTheme {
        /*val movie = Movie(
            0,
            "Suicide Squad",
            "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
            48.261451,
            1466,
            5.91,
            "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg"
        )
        MoviePreviewCard(movie)*/
        MoviePreviewActions()


    }
}

