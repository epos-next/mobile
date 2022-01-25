package epos_next.app.android.feats.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import epos_next.app.android.R

@Composable
fun UserAvatar(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.user_placeholder),
        contentDescription = "your avatar",
        modifier = modifier.size(100.dp).clip(CircleShape)
    )
}