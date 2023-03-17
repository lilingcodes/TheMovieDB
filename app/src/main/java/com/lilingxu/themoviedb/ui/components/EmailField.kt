package com.lilingxu.themoviedb.ui.components

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.ui.theme.TheMovieDBTheme

@Composable
fun EmailField(
    emailText: String,
    onValueChange: (String) -> Unit,
    focusManager: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit = {},
    ) {
    var isEmailFormatValid: Boolean by remember { mutableStateOf(true) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = emailText,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    isEmailFormatValid = Patterns.EMAIL_ADDRESS.matcher(emailText).matches()
                    if (isEmailFormatValid) focusManager()
                }
            ),
            singleLine = true,
            maxLines = 1,
            trailingIcon = trailingIcon
        )
        if (!isEmailFormatValid) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = stringResource(id = R.string.invalid_email),
                color = Color(0xFFAC4343)
            )
        }


    }
}