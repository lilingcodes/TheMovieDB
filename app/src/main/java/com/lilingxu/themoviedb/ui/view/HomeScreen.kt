package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.domain.model.HomeData
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.components.MySpacer
import com.lilingxu.themoviedb.ui.theme.Grey
import com.lilingxu.themoviedb.ui.viewmodel.HomeViewModel
import com.lilingxu.themoviedb.utils.IMAGE_BASE_URL
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    sheetContent: @Composable (ColumnScope.() -> Unit),
    setSheetContent: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(true)
    val popularList: List<Movie> by viewModel.popularMovies.observeAsState(emptyList())
    val nowPlayingList: List<Movie> by viewModel.nowPlayingMovies.observeAsState(emptyList())
    val upcomingList: List<Movie> by viewModel.upcomingMovies.observeAsState(emptyList())
    val topRatedList: List<Movie> by viewModel.topRatedMovies.observeAsState(emptyList())


    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val homeDataList = listOf(
        HomeData(R.string.popular, popularList),
        HomeData(R.string.now_playing, nowPlayingList),
        HomeData(R.string.upcoming, upcomingList),
        HomeData(R.string.top_rated, topRatedList)
    )

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetShape = RoundedCornerShape(16.dp, 16.dp),
        sheetState = sheetState,
        sheetContent = {
            sheetContent()
        }
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {

            LazyColumn(
                modifier = Modifier,
                state = listState
            ) {
                item {
                    MySpacer()
                }
                items(homeDataList) { homeData ->
                    HomeSection(
                        title = stringResource(id = homeData.title),
                        moviesList = homeData.movieList,
                        onClick = { movie ->
                            setSheetContent(movie)
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )
                    MySpacer()
                }

            }
        }

    }

}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    moviesList: List<Movie> = emptyList(),
    onClick: (Movie) -> Unit = {},
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
                MovieItem(movieItem, onClick)
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    movieOnClick: (Movie) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(210.dp)
            .width(140.dp),
        backgroundColor = Grey,
        shape = MaterialTheme.shapes.medium,
        elevation = 10.dp
    ) {
        AsyncImage(
            model = IMAGE_BASE_URL + movie.poster_path,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    movieOnClick(movie)
                }
        )
    }
}



