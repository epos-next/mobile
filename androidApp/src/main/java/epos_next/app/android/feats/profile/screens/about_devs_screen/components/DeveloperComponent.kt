package epos_next.app.android.feats.profile.screens.about_devs_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary

@Composable
fun DeveloperComponent(
    name: String,
    role: String,
    @DrawableRes image: Int,
    onTap: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onTap() }
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        ProfilePicture(image)

        Column(
            modifier = Modifier.padding(start = 15.dp).height(44.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Name(name)
            Role(role)
        }
    }
}

@Composable
private fun ProfilePicture(@DrawableRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
    )
}

@Composable
private fun Name(name: String) {
    Text(text = name, fontSize = 16.sp, color = MaterialTheme.colors.textPrimary)
}

@Composable
private fun Role(role: String) {
    Text(text = role, fontSize = 13.sp, color = MaterialTheme.colors.secondary)
}