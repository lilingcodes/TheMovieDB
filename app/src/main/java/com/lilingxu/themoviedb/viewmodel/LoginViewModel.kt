package com.lilingxu.themoviedb.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.repository.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _loginEnable = MutableLiveData(false)
    val loginEnable: LiveData<Boolean> get() = _loginEnable

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _loginEnable.value = isValidEmail(email) && _password.value != ""
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

