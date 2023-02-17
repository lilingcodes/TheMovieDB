package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.ui.theme.DarkBlue900
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme
import com.lilingxu.themoviedb.ui.viewmodel.DiscoverViewModel

@Composable
fun DiscoverScreen(
    searchFieldOnClick: () -> Unit,
    genreTypeOnClick:(Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {

    val genres: List<Genre> by viewModel.genresList.observeAsState(emptyList())

    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.Center) {
        items(genres) {
            GenreItem(name = it.name) {
                genreTypeOnClick(it.id)
                searchFieldOnClick()
            }
        }
    }
}

@Composable
fun GenreItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onClick() },
        backgroundColor = DarkBlue900,
        shape = RoundedCornerShape(30.dp),
        elevation = 5.dp,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6
            )
        }

    }
}

@Preview
@Composable
fun Pe() {
    TheMovieDBTheme {
        GenreItem(name = "Animation") {
        }
    }
}