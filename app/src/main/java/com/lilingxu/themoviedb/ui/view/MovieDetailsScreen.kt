package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.model.MovieDetails
import com.lilingxu.themoviedb.domain.model.dateText
import com.lilingxu.themoviedb.domain.model.formatMinutes
import com.lilingxu.themoviedb.ui.components.MovieImage
import com.lilingxu.themoviedb.ui.components.MovieStarRating
import com.lilingxu.themoviedb.ui.components.SimpleTopBar
import com.lilingxu.themoviedb.ui.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    arrowBackOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    val movieDetails: MovieDetails by viewModel.movieDetails.collectAsState(MovieDetails())

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopBar() {
                arrowBackOnClick()
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                MovieDetailsHeader(movieDetails = movieDetails)
                Text(
                    text = movieDetails.movie.overview,
                    modifier = Modifier.height(100.dp),
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getMovieDetails(movieId)
    }
}

@Composable
fun MovieDetailsHeader(movieDetails: MovieDetails, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        MovieImage(posterPath = movieDetails.movie.poster_path)
        MovieData(movieDetails = movieDetails)
    }
}

@Composable
private fun MovieData(movieDetails: MovieDetails, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {

        Text(
            text = movieDetails.movie.title,
            style = MaterialTheme.typography.h6,
        )

        MovieStarRating(movieDetails.movie.vote_average, movieDetails.movie.vote_count)


        val textOverLine =
            "${dateText(movieDetails.movie.release_date)} . ${formatMinutes(movieDetails.runtime)} . ${movieDetails.status}"

        Text(
            text = textOverLine,
            style = MaterialTheme.typography.overline,
        )

        LazyRow() {
            items(movieDetails.genres){
                GenreItem(it)
            }
        }

        Text(
            text = movieDetails.tagline,
            style = MaterialTheme.typography.body1.copy(fontStyle = FontStyle.Italic),
        )
    }
}


@Composable
private fun GenreItem(genre: Genre) {
    val color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
    Card(
        modifier = Modifier.padding(8.dp),
        border = BorderStroke(
            1.dp, color
        )
    ) {
        Text(
            text = genre.name,
            color = color,
            modifier = Modifier.padding(8.dp)
        )
    }
}
