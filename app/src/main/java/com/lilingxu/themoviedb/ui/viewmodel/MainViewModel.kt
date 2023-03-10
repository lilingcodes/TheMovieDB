package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.ui.navigation.HomeScreen
import com.lilingxu.themoviedb.ui.navigation.WelcomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _startDestination = MutableStateFlow(WelcomeScreen.route)
    val startDestination: StateFlow<String> get() = _startDestination

    private val _sheetMovie = MutableLiveData<Movie>()
    val sheetMovie: MutableLiveData<Movie> get() = _sheetMovie

    fun setSheetMovie(movie: Movie){
        _sheetMovie.value = movie
    }
    fun updateStartDestination() {
        _startDestination.value = if (sharedPref.getIsLogged()) HomeScreen.route else WelcomeScreen.route
    }
}