package epos_next.app.android.feats.marks.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import epos_next.app.android.R
import epos_next.app.android.components.LessonDivider
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.components.theme.disabled
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.android.feats.marks.main.components.LessonWithMarks
import epos_next.app.android.navigation.Routes
import epos_next.app.state.marks.MarksReducer
import epos_next.app.state.marks.MarksState
import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.get
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun MarksScreen(navController: NavController, scrollState: ScrollState) {
    val reducer = get<MarksReducer>()
    val state = reducer.state.collectAsState().value

    var text by remember { mutableStateOf(TextFieldValue()) }

    ApplicationTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(bottom = 75.dp)
            ) {
                SearchInput(text) { text = it }


                when (state) {
                    is MarksState.Idle -> {
                        for (subject in state.marks) {
                            if (subject.value.periods.isEmpty()) continue
                            LessonDivider()

                            val marks = subject.value.periods.lastOrNull()?.all
                                ?.map { mark -> mark.value }
                                ?: listOf()

                            LessonWithMarks(
                                lessonName = subject.key,
                                totalMark = subject.value.periods.lastOrNull()?.total?.roundToInt(),
                                marks = marks,
                                onClick = { navController.navigate(Routes.Main.Marks.detail(subject.key)) }
                            )
                        }
                    }
                    else -> Unit
                }


            }
        }
    }

}

@Composable
private fun SearchInput(text: TextFieldValue, onChange: (value: TextFieldValue) -> Unit) {
    TextField(
        value = text,
        onValueChange = onChange,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.disabled,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Image(
                modifier = Modifier.size(21.dp),
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search icon"
            )
        },
        textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.textPrimary),
        placeholder = {
            Text(
                "Поиск",
                style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.lightPrimary)
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}