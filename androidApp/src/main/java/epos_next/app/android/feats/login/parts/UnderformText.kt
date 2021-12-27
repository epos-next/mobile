package epos_next.app.android.feats.login.parts

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.lightPrimary

@Composable
fun UnderFormText() {
    Text(
        text = "Email и пароль от аккаунта ЭПОС.Школа",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.lightPrimary,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        modifier = Modifier.fillMaxWidth(),
    )
}