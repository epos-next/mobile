package epos_next.app.android.feats.profile.screens.profile_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary

@Composable
fun Tile(
    text: String,
    @DrawableRes icon: Int,
    color: Color,
    onTap: () -> Unit,
    suffix: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTap() }
            .padding(horizontal = 20.dp, vertical = 12.5.dp)
    ) {
        Icon(icon, color)
        Text(
            text = text,
            modifier = Modifier.padding(start = 15.dp),
            style = TextStyle(
                color = MaterialTheme.colors.textPrimary,
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        suffix()
    }
}

@Composable
private fun Icon(@DrawableRes icon: Int, color: Color) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}