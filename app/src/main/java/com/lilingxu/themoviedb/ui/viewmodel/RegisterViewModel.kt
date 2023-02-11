package com.lilingxu.themoviedb.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _repeatPassword = MutableLiveData("")
    val repeatPassword: LiveData<String> get() = _repeatPassword

    private val _loginEnable = MutableLiveData(false)
    val registerEnable: LiveData<Boolean> get() = _loginEnable

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onRegisterValuesChanged(email: String, password: String, repeatPassword: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword

        _loginEnable.value = isValidEmail(email) && _password.value != "" && _password.value == _repeatPassword.value
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()



    fun register(registerOnClick: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            //TODO crear nuevo usuario api service y google
            delay(2000)
            _isLoading.value = false
            registerOnClick()
        }
    }

}