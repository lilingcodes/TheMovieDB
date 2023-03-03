package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.viewmodel.CreateUsernameViewModel


@Composable
fun CreateUsernameScreen(
    nextOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateUsernameViewModel = hiltViewModel(),
) {

    val username by viewModel.username.observeAsState("")
    val isEnable: Boolean by viewModel.isEnable.observeAsState(false)

    val focusManager = LocalFocusManager.current

    Card(
        modifier = modifier.fillMaxSize().padding(25.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.create_a_username), style = MaterialTheme.typography.h6)

            Text(text = stringResource(id = R.string.create_a_username_text))

            UsernameField(
                username = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                onDone = {
                    if (isEnable) focusManager.clearFocus()
                }
            )

            Button(onClick = nextOnClick, enabled = isEnable) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun UsernameField(
    username: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        OutlinedTextField(
            value = username,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.username)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                val vector = if (true) Icons.Default.CheckCircle else Icons.Default.CheckCircle
                Icon(imageVector = vector, contentDescription = null, Modifier.size(20.dp))
            }
        )
        /*if (!isUsernameValid) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = stringResource(id = R.string.username_already_exists),
                color = Color(0xFFAC4343)
            )
        }*/


    }
}