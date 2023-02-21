package com.lilingxu.themoviedb.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val googleSignInClient: GoogleSignInClient,
) : ViewModel() {

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _isLoginEnable = MutableLiveData(false)
    val isLoginEnable: LiveData<Boolean> get() = _isLoginEnable

    private val _isLogged = MutableLiveData(false)
    val isLogged: LiveData<Boolean> get() = _isLogged

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _isLoginEnable.value = isValidEmail(email) && _password.value != ""
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun login(loginOnClick: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            //TODO autenticacion api service y google
            delay(2000)
            _isLoading.value = false
            loginOnClick()
        }
    }


}

