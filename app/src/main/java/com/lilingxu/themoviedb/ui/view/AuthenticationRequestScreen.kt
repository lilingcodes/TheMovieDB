package com.lilingxu.themoviedb.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref
import com.lilingxu.themoviedb.ui.viewmodel.AuthRequestScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun AuthRequestScreen(
    onSuccess: () -> Unit,
    onFail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthRequestScreenViewModel = hiltViewModel(),
) {

    val sessionId: String by viewModel.sessionId.observeAsState("")
    val secondsLeft: Int by viewModel.secondsLeft.observeAsState(0)

    if (secondsLeft == 0){
        onFail()
    }

    Column(modifier = modifier) {
        Text(text = "Esperando a que acepte")
        Text(text = "$secondsLeft")
        Box(modifier = Modifier.fillMaxWidth()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        Text(text = "Algo ha fallado. volver ")
    }

    LaunchedEffect(true) {
        Log.e("PRUEBAS1", "LaunchEfect")
        val token = sharedPref.getRequestToken()
        Log.e("PRUEBAS", "get token request: ${token}")
        while (token != "") {
            viewModel.createSessionIdWithToken(token)
            if (sessionId != "") {
                onSuccess()
                break
            }
            delay(5000L)
        }

    }

}