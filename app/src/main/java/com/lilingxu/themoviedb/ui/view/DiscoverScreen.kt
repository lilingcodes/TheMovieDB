package com.lilingxu.themoviedb.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.ui.components.SearchTopBar
import com.lilingxu.themoviedb.ui.theme.DarkBlue900
import com.lilingxu.themoviedb.ui.viewmodel.DiscoverViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    searchFieldOnClick: () -> Unit,
    genreTypeOnClick: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val genres: List<Genre> by viewModel.genresList.observeAsState(emptyList())

    val lazyListState = rememberLazyListState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
           SearchTopBar(scrollBehavior)
        },
    ) {

        LazyColumn(
            modifier = modifier.padding(it),
            state = lazyListState,
            verticalArrangement = Arrangement.Center
        ) {
            items(genres) {
                GenreItem(name = it.name) {
                    genreTypeOnClick(it.id, it.name)
                    searchFieldOnClick()
                }
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
            .padding(vertical = 4.dp, horizontal = 16.dp),
        backgroundColor = DarkBlue900,
        shape = RoundedCornerShape(30.dp),
        elevation = 5.dp,
    ) {
        Box(Modifier.clickable { onClick() }, contentAlignment = Alignment.Center) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6
            )
        }

    }
}
