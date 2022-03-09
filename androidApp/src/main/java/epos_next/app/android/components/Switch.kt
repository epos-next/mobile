package epos_next.app.android.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary

@Composable
fun SwitchComponent(enabled: Boolean = false, onTap: (newState: Boolean) -> Unit = {}) {
    val color by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colors.contrast
        else MaterialTheme.colors.lightPrimary
    )

    val padding by animateDpAsState(targetValue = if (enabled) 13.dp else 0.dp)

    Box(
        modifier = Modifier
            .width(32.dp)
            .height(19.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .clickable { onTap(!enabled) }
            .padding(2.dp)
    ) {
        Box(modifier = Modifier
            .padding(start = padding)
            .size(14.dp)
            .clip(CircleShape)
            .background(Color.White))
    }
}