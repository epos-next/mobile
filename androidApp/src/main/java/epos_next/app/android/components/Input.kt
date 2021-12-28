package epos_next.app.android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary

@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    passwordMasking: Boolean = false,
    trailingIcon: @Composable () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .composed { modifier },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.contrast,
            unfocusedBorderColor = MaterialTheme.colors.lightPrimary,
            focusedLabelColor = MaterialTheme.colors.contrast,
            unfocusedLabelColor = MaterialTheme.colors.lightPrimary,
            placeholderColor = MaterialTheme.colors.lightPrimary,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (passwordMasking) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(10.dp),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        maxLines = 1,
        trailingIcon = trailingIcon
    )
}

@Preview(showBackground = true)
@Composable
private fun InputPreview() {
    var value by remember { mutableStateOf("") }
    ApplicationTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            Input(value = value, onValueChange = { value = it }, label = "Email")
        }
    }
}