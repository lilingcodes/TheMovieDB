package com.lilingxu.themoviedb.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.components.EmailField
import com.lilingxu.themoviedb.ui.components.HeaderImage
import com.lilingxu.themoviedb.ui.components.PasswordField
import com.lilingxu.themoviedb.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    forgotPasswordOnClick: () -> Unit,
    loginOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val isLoginEnable: Boolean by viewModel.isLoginEnable.observeAsState(false)
    val isLogged: Boolean by viewModel.isLogged.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val showErrorMessage: Boolean by viewModel.error.observeAsState(false)

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
                    viewModel.login(loginOnClick)
                }
            )

            GoogleLoginButton(
                text = stringResource(id = R.string.login_in_with_google),
                loginButtonOnClick = loginOnClick,
            )

            if (showErrorMessage) {
                Text(
                    text = "An error occurred. Please try again later.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
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
fun LoginButton(text: String, loginEnable: Boolean, loginButtonOnClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = loginButtonOnClick,
        enabled = loginEnable
    ) {
        Text(text = text)
    }
}

@Composable
fun GoogleLoginButton(
    text: String, loginButtonOnClick: () -> Unit,
) {

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnSuccessListener { authResult ->
                        if (authResult.additionalUserInfo?.isNewUser == true) {

                        } else {
                            loginButtonOnClick()
                        }
                    }
            } catch (e: ApiException) {
                Log.e("TAG", "Google sign in failed", e)
            }
        }

    val context = LocalContext.current
    val token = context.getString(R.string.default_web_client_id)
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(context, gso)

            googleSignInClient.signOut()
            launcher.launch(googleSignInClient.signInIntent)
        },
    ) {
        Image(
            modifier = Modifier
                .heightIn(max = 15.dp)
                .padding(end = 8.dp),
            painter = painterResource(id = R.drawable.ic_google_login),
            contentDescription = stringResource(id = R.string.login_in_with_google)
        )
        Text(text = text)
    }
}
