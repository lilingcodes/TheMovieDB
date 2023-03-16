package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.TheMovieDBApplication.Companion.sharedPref
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _username = MutableLiveData<String>("")
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _isLoginEnable = MutableLiveData(false)
    val isLoginEnable: LiveData<Boolean> get() = _isLoginEnable

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage


    fun onLoginChanged(username: String, password: String) {
        _username.value = username.trim()
        _password.value = password.trim()

        _isLoginEnable.value = _username.value != "" && _password.value != ""
    }

    fun loginWithUsernamePassword(username: String, password: String, loginOnClick: () -> Unit) {
        _errorMessage.value = ""
        viewModelScope.launch {
            repository.loginWithUsernamePassword(username, password).collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        val isLoginSuccess = it.data
                        if (isLoginSuccess == true || repository.getAccountSessionId(username) is Resource.Success) {
                            val sessionId = repository.getAccountSessionId(username).data
                            sharedPref.setIsLogged(true)
                            sharedPref.setSessionId(sessionId!!)
                            _isLoading.value = false
                            loginOnClick()
                        } else {
                            _errorMessage.value = "Unexpected error"
                        }
                    }

                    is Resource.Error -> {
                        _errorMessage.value = it.message
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun hiddenErrorMessage() {
        _errorMessage.value = ""
    }
}

