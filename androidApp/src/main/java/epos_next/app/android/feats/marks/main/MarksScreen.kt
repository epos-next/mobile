package epos_next.app.android.feats.marks.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import epos_next.app.android.R
import epos_next.app.android.components.FilledInput
import epos_next.app.android.components.LessonDivider
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.feats.marks.main.components.LessonWithMarks
import epos_next.app.android.navigation.Routes
import epos_next.app.state.marks.MarksReducer
import epos_next.app.state.marks.MarksState
import org.koin.androidx.compose.get
import java.util.*
import kotlin.math.roundToInt

@Composable
fun MarksScreen(navController: NavController, scrollState: ScrollState) {
    val reducer = get<MarksReducer>()
    val viewModel = get<MarksScreenViewModel>()
    val state = reducer.state.collectAsState().value

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(bottom = 75.dp)
        ) {
            SearchInput(viewModel.search) { viewModel.search = it }


            when (state) {
                is MarksState.Idle -> {
                    // filter marks based on search
                    val subjects = state.marks.filter {
                        it.key.lowercase(Locale.getDefault()).contains(viewModel.search.text)
                    }

                    for (subject in subjects) {
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

@Composable
private fun SearchInput(text: TextFieldValue, onChange: (value: TextFieldValue) -> Unit) {
    FilledInput(
        value = text,
        onValueChange = onChange,
        trailingIcon = {
            Image(
                modifier = Modifier.size(21.dp),
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search icon"
            )
        },
        placeholder = "Поиск",
        modifier = Modifier.padding(20.dp),
    )
}