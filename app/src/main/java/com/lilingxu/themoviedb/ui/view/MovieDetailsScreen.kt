package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.*
import com.lilingxu.themoviedb.ui.components.*
import com.lilingxu.themoviedb.ui.theme.DarkBlueLight
import com.lilingxu.themoviedb.ui.view.shimmer.MovieDetailsShimmer
import com.lilingxu.themoviedb.ui.viewmodel.MovieDetailsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    arrowBackOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    val movieDetails: MovieDetails by viewModel.movieDetails.collectAsState(MovieDetails())
    val isLoading: Boolean by viewModel.isLoading.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopBar() {
                arrowBackOnClick()
            }
        }
    ) {
        if (isLoading) {
            MovieDetailsShimmer()
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
            ) {
                item {
                    MovieDetailsHeader(
                        movieDetails = movieDetails,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    MySpacer()

                    DetailsSection("Overview") {
                        MovieOverview(movieDetails)
                    }
                    MySpacer()

                    MovieStateActions(modifier = Modifier.padding(16.dp))
                    MyDivider()

                    DetailsSection("Cast") {
                        MovieCastSection(movieDetails.casts)
                    }
                    MyDivider()

                    DetailsSection("Keywords") { }
                    MyDivider()

                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getMovieDetails(movieId)
    }
}

@Composable
fun MovieCastSection(list: List<Cast>, modifier: Modifier = Modifier) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier, contentPadding = PaddingValues(16.dp)) {
        items(list) {
            CastItem(it)
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .padding(vertical = 10.dp),
        color = DarkBlueLight
    )
}

@Composable
fun CastItem(cast: Cast, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .height(200.dp)
            .width(96.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
    ) {
        Column() {
            SimpleImage(
                posterPath = cast.profile_path,
                shape = MaterialTheme.shapes.medium.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 3.dp),
                text = cast.original_name,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
            )
            Text(modifier = Modifier.padding(horizontal = 3.dp), text = cast.character)
        }
    }
}

@Composable
fun DetailsSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    //viewMore: Boolean = false,
    //viewMoreOnClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            Text(
                modifier = modifier.padding(horizontal = 16.dp),
                text = title,
                style = MaterialTheme.typography.subtitle2,
            )
            /* if (viewMore) {
                 Text(
                     modifier = Modifier.clickable { viewMoreOnClick() },
                     text = "View more",
                     style = MaterialTheme.typography.body1,
                 )
             }*/

        }
        content()
    }
}

@Composable
fun MovieOverview(movieDetails: MovieDetails, modifier: Modifier = Modifier) {

    if (movieDetails.tagline.isNotEmpty()) {
        Text(
            modifier= modifier.padding(horizontal = 16.dp),
            text = movieDetails.tagline,
            style = MaterialTheme.typography.body2.copy(fontStyle = FontStyle.Italic),
        )
    }
    Text(
        modifier= modifier.padding(horizontal = 16.dp),
        text = movieDetails.movie.overview,
        style = MaterialTheme.typography.body2,
        overflow = TextOverflow.Ellipsis,
    )

}

@Composable
fun MovieDetailsHeader(movieDetails: MovieDetails, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        SimpleImage(
            posterPath = movieDetails.movie.poster_path,
            Modifier.padding(start = 16.dp, end = 8.dp)
        )
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

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 8.dp)
        ) {
            items(movieDetails.genres) {
                GenreItem(it)
            }
        }


    }
}


@Composable
private fun GenreItem(genre: Genre) {
    val color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
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
