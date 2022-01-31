package epos_next.app.android.feats.marks.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import epos_next.app.android.components.LessonDivider
import epos_next.app.android.feats.marks.detail.components.CollapsedPeriodMarks
import epos_next.app.android.feats.marks.detail.components.PrimaryMarkRow
import epos_next.app.android.R
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.android.helpers.UiHelper
import epos_next.app.state.marks.MarksReducer
import epos_next.app.state.marks.MarksState
import epos_next.app.utils.FormatHelper
import org.koin.androidx.compose.get
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MarksDetailScreen(navController: NavHostController, subject: String) {
    val reducer = get<MarksReducer>()
    val state = reducer.state.collectAsState().value

    ApplicationTheme {
        Scaffold {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 75.dp)
            ) {

                when (state) {
                    is MarksState.Idle -> {
                        val lesson = state.marks.entries.firstOrNull { it.key == subject }

                        SubjectName(subject, navController)

                        if (lesson != null && lesson.value.periods.isNotEmpty()) {
                            val periods = lesson.value.periods

                            val totalExpected = if (periods.isNotEmpty()) {
                                periods.sumOf { it.total ?: 0.0 } /
                                        periods.count { it.total != null }
                            } else null

                            val total = lesson.value.total?.roundToInt()

                            if (totalExpected != null) {
                                PrimaryMarkRow(
                                    modifier = Modifier.padding(
                                        top = 15.dp,
                                        start = 20.dp,
                                        end = 20.dp
                                    ),
                                    name = "Средняя оценка за год",
                                    value = "%.2f".format(totalExpected).replace(',', '.')
                                )
                            }

                            if (total != null) {
                                PrimaryMarkRow(
                                    modifier = Modifier.padding(
                                        top = 15.dp,
                                        start = 20.dp,
                                        end = 20.dp
                                    ),
                                    name = "Выставленная оценка за год",
                                    value = "$total"
                                )
                            }

                            LessonDivider(modifier = Modifier.padding(top = 30.dp))
                            for ((i, period) in periods.withIndex()) {
                                CollapsedPeriodMarks(
                                    text = "${i + 1} четверть",
                                    period = period,
                                    initiallyOpen = i + 1 == periods.size,
                                )
                                LessonDivider()
                            }
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}

@Composable
private fun SubjectName(text: String, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 15.dp),
    ) {
        IconButton(
            modifier = Modifier
                .padding(end = 20.dp)
                .size(26.dp),
            onClick = { navController.popBackStack() }
        ) {
            Image(
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "back icon"
            )
        }

        Text(
            FormatHelper.formatSubjectName(text),
            style = TextStyle(
                color = MaterialTheme.colors.textPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
            )
        )
    }
}
