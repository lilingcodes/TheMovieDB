package com.lilingxu.themoviedb.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.components.EmailField
import com.lilingxu.themoviedb.ui.components.HeaderImage
import com.lilingxu.themoviedb.ui.components.PasswordField
import com.lilingxu.themoviedb.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    forgotPasswordOnClick: () -> Unit,
    loginOnClick: () -> Unit,
    viewModel: LoginViewModel,
) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val focusManager = LocalFocusManager.current


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
                onValueChange = { viewModel.onLoginChanged(it, password) },
                focusManager = { focusManager.moveFocus(FocusDirection.Down) }
            )
            Spacer(Modifier.height(20.dp))

            PasswordField(
                label = stringResource(id = R.string.password),
                password = password,
                onValueChanged = { viewModel.onLoginChanged(email, it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(Modifier.height(20.dp))

            ForgotPasswordText(Modifier
                .align(Alignment.End)
                .clickable { forgotPasswordOnClick() }
            )
            Spacer(Modifier.height(20.dp))

            LoginButton(loginEnable = loginEnable) {
                viewModel.login(loginOnClick)
            }
        }
    }
}


@Composable
fun ForgotPasswordText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.forgot_password),
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, loginButtonOnClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = loginButtonOnClick,
        enabled = loginEnable
    ) {
        Text(text = stringResource(id = R.string.login_in))
    }
}