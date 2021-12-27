package epos_next.app.android.feats.login.parts

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.ErrorText
import epos_next.app.android.components.Input
import epos_next.app.android.components.PrimaryButton
import epos_next.app.state.authStatus.AuthStatusReducer
import org.koin.androidx.compose.get

@ExperimentalAnimationApi
@Composable
fun Form() {
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val disabled = email.isEmpty() || password.isEmpty() || error.isNotEmpty()

    val authStatusReducer = get<AuthStatusReducer>()

    Input(
        value = email,
        onValueChange = { email = it },
        label = "Email",
        placeholder = "Enter your email",
        keyboardActions = KeyboardActions(
            onGo = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Email
        ),
    )
    Spacer(modifier = Modifier.height(10.dp))

    Input(
        value = password,
        onValueChange = { password = it },
        label = "Password",
        placeholder = "Enter your password",
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        passwordMasking = false,
    )
    Spacer(modifier = Modifier.height(18.dp))

    PrimaryButton(
        text = "Войти",
        onClick = { error = "test" },
        disabled = disabled,
    )

    Spacer(modifier = Modifier.height(10.dp))

    AnimatedContent(
        targetState = error.isEmpty(),
        transitionSpec = {
            if (targetState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        },
        modifier = Modifier.height(22.dp)
    ) { targetDisabled ->
        if (targetDisabled) UnderFormText()
        else ErrorText(
            error,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }

}