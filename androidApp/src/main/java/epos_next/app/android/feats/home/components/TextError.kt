package epos_next.app.android.feats.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.lightError

private const val defaultArg = "Something bad happened"

@Preview
@Composable
fun TextError(modifier: Modifier = Modifier, error: String = defaultArg) {
    Box(
        modifier = Modifier
            .composed { modifier }
            .fillMaxWidth()
            .background(MaterialTheme.colors.lightError.copy(alpha = 0.5F))
            .padding(vertical = 15.dp, horizontal = 15.dp),
    ) {
        Text(
            text = error,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
        )
    }
}