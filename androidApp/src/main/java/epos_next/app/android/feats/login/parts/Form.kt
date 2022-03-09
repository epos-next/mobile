package epos_next.app.android.feats.login.parts

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import epos_next.app.android.R
import epos_next.app.android.components.ButtonState
import epos_next.app.android.components.ErrorText
import epos_next.app.android.components.Input
import epos_next.app.android.components.PrimaryButton
import epos_next.app.domain.exceptions.translateException
import epos_next.app.state.user.UserReducer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalAnimationApi
@Composable
fun Form() {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val disabled = email.isEmpty() || password.isEmpty() || error.isNotEmpty()

    val authStatusReducer = get<UserReducer>()

    val login: () -> Unit = {
        loading = true
        coroutineScope.launch {
            val failure = authStatusReducer.login(email, password)
            if (failure != null) error = translateException(failure)
            else print("success")
            loading = false
        }
    }

    Input(
        value = email,
        onValueChange = {
            email = it
            if (error.isNotEmpty()) error = ""
        },
        label = "Email",
        placeholder = "Введите ваш email",
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
        onValueChange = {
            password = it
            if (error.isNotEmpty()) error = ""
        },
        label = "Пароль",
        placeholder = "Введите ваш пароль",
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        passwordMasking = !passwordVisible,
        trailingIcon = {
            val id = if (passwordVisible) R.drawable.ic_eye else R.drawable.ic_eye_closed
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Image(
                    painterResource(id = id),
                    "eye",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )
    Spacer(modifier = Modifier.height(18.dp))

    PrimaryButton(
        text = "Войти",
        onClick = { login() },
        disabled = disabled,
        state = if (loading) ButtonState.Loading else ButtonState.Idle,
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
        modifier = Modifier.requiredHeight(22.dp)
    ) { targetDisabled ->
        if (targetDisabled) UnderFormText()
        else ErrorText(
            error,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }

}