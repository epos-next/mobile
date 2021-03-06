package epos_next.app.android.feats.home.sheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import epos_next.app.state.advertisements.AdvertisementsReducer
import kotlinx.datetime.LocalDateTime
import org.koin.androidx.compose.get

@Composable
fun ColumnScope.AdvertisementBottomSheet(close: () -> Unit) {
    val reducer = get<AdvertisementsReducer>()

    var content by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf<LocalDateTime?>(null) }

    fun handleTap() {
        // validation
        if (content.text.trim().isEmpty()) return
        if (date == null) return

        reducer.createAd(
            content = content.text,
            date = date!!,
        )

        Firebase.analytics.logEvent("create_ad") {
            param(FirebaseAnalytics.Param.ITEM_NAME, "Create Advertisement")
            param(FirebaseAnalytics.Param.VALUE, content.text)
            param("date", date.toString())
        }

        close()
    }

    SheetTitle("Новое объявление")
    FilledInput(
        value = content,
        onValueChange = { content = it},
        placeholder = "Название"
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
        AdvertisementBottomSheet(close = {})
    }
}