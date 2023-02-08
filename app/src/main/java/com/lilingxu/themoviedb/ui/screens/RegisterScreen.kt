package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.components.HeaderImage
import com.lilingxu.themoviedb.ui.components.EmailField
import com.lilingxu.themoviedb.ui.components.PasswordField
import com.lilingxu.themoviedb.viewmodel.RegisterViewModel


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerOnClick: () -> Unit,
    viewModel: RegisterViewModel,
) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val repeatPassword: String by viewModel.repeatPassword.observeAsState("")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val focusManager = LocalFocusManager.current
    var isPasswordEqual: Boolean by remember { mutableStateOf(true) }


    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderImage()
            Spacer(Modifier.height(40.dp))

            EmailField(
                emailText = email,
                onValueChange = { viewModel.onRegisterValuesChanged(it, password, repeatPassword) },
                focusManager = { focusManager.moveFocus(FocusDirection.Next) }
            )
            Spacer(Modifier.height(20.dp))

            PasswordField(
                label = stringResource(id = R.string.password),
                password = password,
                onValueChanged = { viewModel.onRegisterValuesChanged(email, it, repeatPassword) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )
            Spacer(Modifier.height(20.dp))

            PasswordField(
                label = stringResource(id = R.string.repeat_password),
                password = repeatPassword,
                onValueChanged = { viewModel.onRegisterValuesChanged(email, password, it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        isPasswordEqual = password == repeatPassword

                    }
                )
            )

            if (!isPasswordEqual) {
                Text(
                    modifier = Modifier.align(Alignment.Start).padding(start = 15.dp),
                    text = stringResource(id = R.string.passwords_do_not_match),
                    color = Color(0xFFAC4343)
                )
            }
            Spacer(Modifier.height(20.dp))

            RegisterButton(registerEnable = registerEnable) {
                viewModel.register {
                    registerOnClick()
                }
            }
        }
    }
}

@Composable
fun RegisterButton(registerEnable: Boolean, registerOnClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = registerOnClick,
        enabled = registerEnable
    ) {
        Text(text = stringResource(id = R.string.login_in))
    }
}