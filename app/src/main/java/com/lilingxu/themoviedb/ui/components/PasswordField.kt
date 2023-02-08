package com.lilingxu.themoviedb.ui.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.R

@Composable
fun PasswordField(
    label: String,
    password: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions

) {

    var hidden by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = password,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        visualTransformation =
        if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = { hidden = !hidden }) {
                val vector = painterResource(
                    if (hidden) R.drawable.ic_visibility
                    else R.drawable.ic_visibility_off
                )
                val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña"
                Icon(painter = vector, contentDescription = description, Modifier.size(20.dp))
            }
        }
    )
}