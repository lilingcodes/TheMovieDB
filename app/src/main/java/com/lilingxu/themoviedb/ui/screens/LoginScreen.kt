package com.lilingxu.themoviedb.ui.screens

import android.content.Intent
import android.util.Patterns
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.components.EmailField
import com.lilingxu.themoviedb.ui.components.HeaderImage
import com.lilingxu.themoviedb.ui.components.PasswordField
import com.lilingxu.themoviedb.ui.components.SimpleAlertDialog
import com.lilingxu.themoviedb.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    forgotPasswordOnClick: () -> Unit,
    loginOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val username: String by viewModel.username.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val isLoginEnable: Boolean by viewModel.isLoginEnable.observeAsState(false)
    val errorMessage: String by viewModel.errorMessage.observeAsState("")

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

            UsernameField(
                text = username,
                onValueChange = { viewModel.onLoginChanged(it, password) },
                focusManager = { focusManager.moveFocus(FocusDirection.Down) }
            )
            Spacer(Modifier.height(20.dp))

            PasswordField(
                label = stringResource(id = R.string.password),
                password = password,
                onValueChanged = { password ->
                    viewModel.onLoginChanged(username, password)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(Modifier.height(20.dp))

            ForgotPasswordText(
                Modifier
                    .align(Alignment.End)
                    .clickable { forgotPasswordOnClick() }
            )
            Spacer(Modifier.height(20.dp))

            LoginButton(
                text = stringResource(id = R.string.login_in),
                loginEnable = isLoginEnable,
                loginButtonOnClick = {
                    viewModel.loginWithUsernamePassword(username, password, loginOnClick)
                }
            )

            if (errorMessage != "") {
                SimpleAlertDialog(
                    title = "There was an error processing your login",
                    body = errorMessage
                ) {
                    viewModel.hiddenErrorMessage()
                }
            }
        }
    }
}

@Composable
fun UsernameField(
    text: String,
    onValueChange: (String) -> Unit,
    focusManager: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.username)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager() }
        ),
        singleLine = true,
        maxLines = 1,
    )

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
fun LoginButton(text: String, loginEnable: Boolean, loginButtonOnClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = loginButtonOnClick,
        enabled = loginEnable
    ) {
        Text(text = text)
    }
}
