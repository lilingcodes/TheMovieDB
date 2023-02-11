package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.theme.Grey
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme
import com.lilingxu.themoviedb.ui.viewmodel.HomeViewModel
import com.lilingxu.themoviedb.utils.IMAGE_BASE_URL


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val popularList: List<Movie> by viewModel.popularMovies.observeAsState(emptyList())
    val nowPlayingList: List<Movie> by viewModel.nowPlayingMovies.observeAsState(emptyList())
    val upcomingList: List<Movie> by viewModel.upcomingMovies.observeAsState(emptyList())
    val topRatedList: List<Movie> by viewModel.topRatedMovies.observeAsState(emptyList())

    LazyColumn(
        modifier = modifier,//.heightIn(max = 2000.dp)
    ) {
        item {
            Spacer(Modifier.height(50.dp))
        }

        item {
            HomeSection(title = stringResource(id = R.string.popular), moviesList = popularList)
            Spacer(Modifier.height(16.dp))
            HomeSection(title = stringResource(id = R.string.now_playing),moviesList = nowPlayingList)
            Spacer(Modifier.height(16.dp))
            HomeSection(title = stringResource(id = R.string.upcoming), moviesList = upcomingList)
            Spacer(Modifier.height(16.dp))
            HomeSection(title = stringResource(id = R.string.top_rated), moviesList = topRatedList)
        }

        item {
            Spacer(Modifier.height(50.dp))
        }
    }

}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    title: String,
    moviesList: List<Movie> = emptyList(),
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = title,
            style = MaterialTheme.typography.h5
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        {
            items(moviesList) { movieItem ->
                MovieItem(movieItem)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(140.dp),
        backgroundColor = Grey,
        shape = MaterialTheme.shapes.medium,
        elevation = 10.dp
    ) {
        AsyncImage(
            model = IMAGE_BASE_URL + movie.poster_path,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview
@Composable
fun MovieItemPreview() {
    TheMovieDBTheme {
        val movie = Movie(
            123,
            "Run",
            5.8,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/ibjKNVEk348eIXnvwEz2wC45MGs.jpg"
        )
        MovieItem(movie = movie)
    }

}