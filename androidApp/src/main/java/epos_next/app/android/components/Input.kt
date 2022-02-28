package epos_next.app.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.*

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

@Composable
fun FilledInput(
    value: String,
    name: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable () -> Unit = {},
    singleLine: Boolean = true,
) {
    Column {
        Text(
            text = name,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondary,
        )

        Spacer(modifier = Modifier.height(7.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.disabled,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colors.textPrimary
            ),
            placeholder = {
                Text(
                    placeholder,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.lightPrimary
                    )
                )
            },
            shape = RoundedCornerShape(10.dp),
            singleLine = singleLine,
        )
    }
}

@Composable
fun FilledInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable () -> Unit = {},
    readOnly: Boolean = false,
) {
    TextField(
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.disabled,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = MaterialTheme.colors.textPrimary
        ),
        placeholder = {
            Text(
                placeholder,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.lightPrimary
                )
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun InputPreview() {
    var value by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf(TextFieldValue()) }

    ApplicationTheme {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Input(value = value, onValueChange = { value = it }, label = "Email")
            Spacer(Modifier.height(10.dp))
            FilledInput(value = value2, onValueChange = { value2 = it }, placeholder = "Search")
        }
    }
}