package epos_next.app.android.feats.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary

@Preview
@Composable
fun HomeTitle(modifier: Modifier = Modifier, text: String = "Next lesson") {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            color = MaterialTheme.colors.textPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.W500,
        )
    )
}