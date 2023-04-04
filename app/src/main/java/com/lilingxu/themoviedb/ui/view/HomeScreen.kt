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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lilingxu.themoviedb.domain.model.HomeData
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.components.MySpacer
import com.lilingxu.themoviedb.ui.theme.Grey
import com.lilingxu.themoviedb.ui.view.shimmer.HomeShimmer
import com.lilingxu.themoviedb.ui.viewmodel.HomeViewModel
import com.lilingxu.themoviedb.utils.IMAGE_BASE_URL
import com.lilingxu.themoviedb.utils.IMAGE_HEIGHT_MEDIUM
import com.lilingxu.themoviedb.utils.IMAGE_WIDTH_MEDIUM
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
    val homeDataList: List<HomeData> by viewModel.homeDataList.observeAsState(emptyList())


    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        modifier = modifier,
        sheetShape = RoundedCornerShape(16.dp, 16.dp),
        sheetState = sheetState,
        sheetContent = {
            sheetContent()
        }
    ) {
        if (isLoading) {
            HomeShimmer()
        } else {
            LazyColumn(
                modifier = Modifier,
                state = listState
            ) {

                items(homeDataList) { homeData ->
                    MySpacer()
                    HomeSection(
                        title = homeData.title,
                        moviesList = homeData.movieList,
                        onClick = { movie ->
                            setSheetContent(movie)
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )

                }
                item{
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
            .height(IMAGE_HEIGHT_MEDIUM)
            .width(IMAGE_WIDTH_MEDIUM),
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



