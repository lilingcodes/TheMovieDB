package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.viewmodel.GenreViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreScreen(
    genreId: Int,
    genreName: String,
    arrowBackOnClick: () -> Unit,
    sheetContent: @Composable() (ColumnScope.() -> Unit),
    setSheetContent: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GenreViewModel = hiltViewModel(),
    ) {

    val movieList: List<Movie> by viewModel.movieList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(16.dp, 16.dp),
        modifier = modifier,
        sheetState = sheetState,
        sheetContent = {
            sheetContent()
        }
    ) {
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
            MovieGrid(movieList, listState, it, movieOnClick = { movie ->
                setSheetContent(movie)
                scope.launch {
                    sheetState.show()
                }
            })
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrolledToEnd() }
            .filter { it }
            .collect {
                if (it && !isLoading) {
                    viewModel.loadNextPage(genreId)
                }
            }
    }
}


@Composable
fun MovieGrid(
    movieList: List<Movie>,
    listState: LazyGridState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    movieOnClick: (Movie) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier.padding(paddingValues),
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
            MovieItem(it,movieOnClick)
        }

    }
}

fun LazyGridState.isScrolledToEnd(): Boolean {
    val lastVisibleIndex = this.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    val totalCount = this.layoutInfo.totalItemsCount
    return lastVisibleIndex >= totalCount - 1
}