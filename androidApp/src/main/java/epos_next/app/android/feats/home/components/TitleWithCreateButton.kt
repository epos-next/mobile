package epos_next.app.android.feats.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.R

@Preview
@Composable
fun TitleWithCreateButton(
    modifier: Modifier = Modifier,
    text: String = "Контрольные работы",
    onTap: () -> Unit = {}
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .composed { modifier }, Arrangement.SpaceBetween) {
        HomeTitle(text = text)
        Image(
            modifier = Modifier
                .size(18.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onTap() },
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = "Create icon"
        )
    }

}