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
import epos_next.app.android.components.*
import epos_next.app.android.feats.home.components.SheetTitle
import epos_next.app.state.marks.MarksReducer
import kotlinx.datetime.LocalDateTime
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.AdvertisementBottomSheet() {
    val marksReducer = get<MarksReducer>()

    var name by remember { mutableStateOf(TextFieldValue()) }
    var subject by remember { mutableStateOf("") }
    var date by remember { mutableStateOf<LocalDateTime?>(null) }

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
    PrimaryButton(text = "Создать")
}


@Preview
@Composable
private fun SheetPreview() {
    BottomSheetWithCloseDialog {
        AdvertisementBottomSheet()
    }
}