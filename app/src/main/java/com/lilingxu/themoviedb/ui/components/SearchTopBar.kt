package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.TopAppBar(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        title = {},
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colors.background),
        navigationIcon = {
            SearchField(true)
        },
        scrollBehavior = scrollBehavior
    )


}

@Composable
fun SearchField(
    readOnly: Boolean,
    modifier: Modifier = Modifier,
    text: TextFieldValue = TextFieldValue(""),
    onValueChange: (TextFieldValue) -> Unit = {},
) {
    TextField(
        value = text,
        readOnly = readOnly,
        onValueChange = onValueChange,
        maxLines = 1,
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
            .fillMaxWidth()
            .padding(end = 5.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Clear Icon"
                )
            }
        },
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) }

    )
}