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

    private val _requestToken = MutableLiveData<String>("")
    val requestToken: MutableLiveData<String> get() = _requestToken

    private val _sessionId = MutableLiveData<String>("")
    val sessionId: MutableLiveData<String> get() = _sessionId

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
            authRepository.registerWithTMDB().collect {
                when (it) {
                    is Resource.Success -> {
                        val requestToken = it.data ?: "success null"
                        Log.e("PRUEBAS1", "guardo token $requestToken")
                        _requestToken.value = requestToken
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

    fun createSessionIdWithToken(requestToken: String) {
        viewModelScope.launch {
            Log.e("PRUEBAS", "get token request ${requestToken}")
            authRepository.createSession(requestToken).collect {
                when (it) {
                    is Resource.Loading ->{
                        Log.e("PRUEBAS", "SESSION LOADING")

                    }
                    is Resource.Success -> {
                        val sessionId = it.data ?: ""
                        sharedPref.setSessionId(sessionId)
                        _sessionId.value = sessionId
                        saveNewUser(sessionId)
                        Log.e("PRUEBAS", "SESSION: ${sessionId}")
                    }
                    else -> {
                        Log.e("PRUEBAS", "SESSION FALLIDA")
                    }
                }
            }
        }
    }

    fun saveNewUser(sessionId: String) {
        viewModelScope.launch {
            authRepository.getUserDetails(sessionId).collect {
                when(it){
                    is Resource.Loading ->{
                    }
                    is Resource.Success ->{
                        val account = it.data
                        if (account != null) {
                            authRepository.saveNewUser(sessionId, account).collect{
                                when(it){
                                    is Resource.Loading ->{

                                    }
                                    is Resource.Success ->{
                                        Log.e("PRUEBAS", "USER GURDADO")

                                    }
                                    is Resource.Error ->{
                                        Log.e("PRUEBAS", "USER ERROR")
                                    }
                                }
                            }
                        }
                        
                        Log.e("PRUEBAS", "USER: ${sessionId}")

                    }
                    is Resource.Error ->{
                        Log.e("PRUEBAS", "USER ERROR ")

                    }
                }
            }
        }
    }
    
    
}
