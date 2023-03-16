package com.lilingxu.themoviedb.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import com.lilingxu.themoviedb.ui.navigation.HomeScreen
import com.lilingxu.themoviedb.ui.navigation.WelcomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _startDestination = MutableStateFlow(WelcomeScreen.route)
    val startDestination: StateFlow<String> get() = _startDestination

    private val _sheetMovie = MutableLiveData<Movie>()
    val sheetMovie: MutableLiveData<Movie> get() = _sheetMovie

    fun setSheetMovie(movie: Movie) {
        _sheetMovie.value = movie
    }

    fun updateStartDestination() {
        _startDestination.value =
            if (sharedPref.getIsLogged()) HomeScreen.route else WelcomeScreen.route
    }

    fun registerWithTMDB(context: Context) {
        viewModelScope.launch {
            when (val resource = authRepository.registerWithTMDB()) {
                is Resource.Success -> {
                    val requestToken = resource.data ?: "success null"
                    Log.e("PRUEBAS1", "guardo token $requestToken")
                    sharedPref.setRequestToken(requestToken)
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.themoviedb.org/authenticate/$requestToken")
                    )
                    context.startActivity(urlIntent)
                }
                else -> {

                }
            }
        }
    }
}
