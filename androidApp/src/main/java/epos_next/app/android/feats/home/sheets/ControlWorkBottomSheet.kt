package epos_next.app.android.feats.home.sheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import epos_next.app.android.components.*
import epos_next.app.android.feats.home.components.SheetTitle
import epos_next.app.state.marks.MarksReducer
import epos_next.app.state.schoolTests.SchoolTestsReducer
import kotlinx.datetime.LocalDateTime
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.ControlWorkBottomSheet(close: () -> Unit) {
    val marksReducer = get<MarksReducer>()
    val schoolTestsReducer = get<SchoolTestsReducer>()

    var name by remember { mutableStateOf(TextFieldValue()) }
    var subject by remember { mutableStateOf("") }
    var date by remember { mutableStateOf<LocalDateTime?>(null) }

    fun handleTap() {
        // validation
        if (name.text.trim().isEmpty()) return
        if (subject.trim().isEmpty()) return
        if (date == null) return

        schoolTestsReducer.createSchoolTest(
            name = name.text,
            subject = subject,
            date = date!!,
        )

        Firebase.analytics.logEvent("create_school_test") {
            param(FirebaseAnalytics.Param.ITEM_NAME, "Create School Test")
            param("name", name.text)
            param("subject", subject)
            param("date", date.toString())
        }

        close()
    }

    SheetTitle("Новая контрольная работа")
    FilledInput(
        value = name,
        onValueChange = { name = it},
        placeholder = "Название"
    )
    Spacer(Modifier.height(20.dp))
    FilledDropDownInput(
        options = marksReducer.getSubjectNames(),
        onSelect = { subject = it },
        placeholder = "Предмет",
    )
    Spacer(Modifier.height(20.dp))
    FilledDatePickerInput(
        placeholder = "Дата",
        onChange = { date = it }
    )
    Spacer(Modifier.height(20.dp))
    PrimaryButton(text = "Создать", onClick = ::handleTap)
}


@Preview
@Composable
private fun SheetPreview() {
    BottomSheetWithCloseDialog {
        ControlWorkBottomSheet(close = {})
    }
}