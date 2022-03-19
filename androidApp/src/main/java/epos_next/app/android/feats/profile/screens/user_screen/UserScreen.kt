package epos_next.app.android.feats.profile.screens.user_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import epos_next.app.android.R
import epos_next.app.android.components.*
import epos_next.app.android.feats.profile.components.ProfileHeader
import epos_next.app.state.user.UserReducer
import epos_next.app.state.user.UserState
import epos_next.app.utils.validateUserName
import epos_next.app.utils.validateUserUsername
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.koin.androidx.compose.get

@Composable
fun UserScreen(
    rootNavController: NavHostController,
    scrollState: ScrollState,
) {
    val reducer = get<UserReducer>()
    val state = reducer.state.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    if (state is UserState.Authorized) {
        var name by remember { mutableStateOf(state.user.name) }
        var username by remember { mutableStateOf(state.user.username) }
        var dateOfBirth by remember { mutableStateOf<LocalDateTime?>(state.user.dateOfBirth) }

        var nameError by remember { mutableStateOf<String?>(null) }
        var usernameError by remember { mutableStateOf<String?>(null) }

        var buttonState by remember { mutableStateOf<ButtonState>(ButtonState.Idle) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 65.dp)
                .background(MaterialTheme.colors.background)
        ) {
            ProfileHeader(
                image = R.drawable.user_icon_96,
                color = Color(0xFF68D676),
                text = "Профиль",
                navController = rootNavController,
            )

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                val focusManager = LocalFocusManager.current
                val actions = KeyboardActions(onGo = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
                val options = KeyboardOptions.Default.copy(imeAction = ImeAction.Go)

                FilledInput(
                    value = name,
                    name = "Имя",
                    placeholder = "Введите ваше имя",
                    onValueChange = { name = it },
                    singleLine = true,
                    keyboardActions = actions,
                    keyboardOptions = options,
                    error = nameError,
                )
                Spacer(modifier = Modifier.size(20.dp))

                FilledInput(
                    value = username,
                    name = "Username",
                    placeholder = "Введите ваш username",
                    onValueChange = { username = it },
                    keyboardActions = actions,
                    keyboardOptions = options,
                    error = usernameError,
                )
                Spacer(modifier = Modifier.size(20.dp))

                FilledDatePickerInput(
                    placeholder = "Введите вашу дату рождения",
                    name = "Дата рождения",
                    onChange = { dateOfBirth = it },
                    value = dateOfBirth,
                    onlyPast = true,
                )

                Spacer(modifier = Modifier.height(20.dp))

                PrimaryButton(
                    text = "Сохранить",
                    state = buttonState,
                    onClick = {
                        // validation
                        nameError = validateUserName(name)
                        usernameError = validateUserUsername(username)

                        if (nameError == null && usernameError == null) {
                            coroutineScope.launch {
                                buttonState = ButtonState.Loading
                                delay(2000)
                                reducer.update(name, username, dateOfBirth)
                                buttonState = ButtonState.Done
                                delay(2000)
                                buttonState = ButtonState.Idle
                            }
                        }
                    }
                )
            }
        }
    }
}