package com.lilingxu.themoviedb.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.lilingxu.themoviedb.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun AuthRequestScreen(
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    onFail: () -> Unit = {},
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val requestToken: String by mainViewModel.requestToken.observeAsState("")
    val sessionId: String by mainViewModel.sessionId.observeAsState("")

    Column(modifier = modifier) {
        Text(text = "Esperando a que acepte")
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        Text(text = "Algo ha fallado. volver")
    }

    LaunchedEffect(true, requestToken) {
        Log.e("PRUEBAS1", "LaunchEfect")
        val token = sharedPref.getRequestToken()
        while (token != "") {
            mainViewModel.createSessionIdWithToken(token)
            if (sessionId != "") {

                onSuccess()
                break
            }
            delay(5000L)
        }

    }

}