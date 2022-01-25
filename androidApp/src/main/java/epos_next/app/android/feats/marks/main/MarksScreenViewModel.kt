package epos_next.app.android.feats.marks.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class MarksScreenViewModel: ViewModel() {
    var search by mutableStateOf(TextFieldValue())
}