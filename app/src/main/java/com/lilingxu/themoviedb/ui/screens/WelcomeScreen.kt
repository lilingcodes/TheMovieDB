package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    loginOnClick: () -> Unit,
    registerOnClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_themoviedb),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = loginOnClick) {
            Text(text = stringResource(id = R.string.login_in))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = registerOnClick) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}

