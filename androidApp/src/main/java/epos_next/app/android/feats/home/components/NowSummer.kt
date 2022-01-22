package epos_next.app.android.feats.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NowSummerMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp),
    ) {
        Text(
            text = "Сейчас лето, иди отдыхай, дурачек)",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}