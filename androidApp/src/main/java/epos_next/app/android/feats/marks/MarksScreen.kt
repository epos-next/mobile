package epos_next.app.android.feats.marks

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
import epos_next.app.android.R
import epos_next.app.android.components.LessonDivider
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.components.theme.disabled
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.android.feats.marks.components.LessonWithMarks

@Composable
fun MarksScreen() {
    var text by remember { mutableStateOf(TextFieldValue()) }

    ApplicationTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 75.dp)
            ) {
                SearchInput(text) { text = it }

                for (i in 1..8) {
                    LessonDivider()
                    LessonWithMarks(onClick = { /* TODO */ })
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