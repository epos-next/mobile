package epos_next.app.android.feats.home.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary

@Composable
fun ColumnScope.SheetTitle(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = MaterialTheme.colors.textPrimary,
        modifier = Modifier.padding(bottom = 20.dp).align(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Medium,
    )
}