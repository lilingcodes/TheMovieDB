package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.viewmodel.GenreViewModel
import kotlinx.coroutines.flow.filter


@Composable
fun GenreScreen(
    genreId: Int,
    genreName: String,
    arrowBackOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GenreViewModel = hiltViewModel(),
) {

    viewModel.getMoviesByGenre(genreId)
    val movieList: List<Movie> by viewModel.movieList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val listState = rememberLazyGridState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = arrowBackOnClick) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(genreName)
                }
            )
        }
    ) {
        MovieGrid(movieList, listState)
    }


    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrolledToEnd() }
            .filter { it }
            .collect {
                if (it) {
                    viewModel.loadNextPage(genreId)
                }
            }
    }

}

@Composable
fun MovieGrid(movieList: List<Movie>, listState: LazyGridState, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 12.dp,
        ),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movieList) {
            MovieItem(movie = it)
        }

    }
}

fun LazyGridState.isScrolledToEnd(): Boolean {
    val lastVisibleIndex = this.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    val totalCount = this.layoutInfo.totalItemsCount
    return lastVisibleIndex >= totalCount - 1
}