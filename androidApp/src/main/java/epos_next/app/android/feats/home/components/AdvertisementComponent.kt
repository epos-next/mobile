package epos_next.app.android.feats.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.domain.entities.Advertisement
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private val defaultProps = Advertisement(
    id = 1,
    content = "Не забудьте сдать работу",
    targetDate = Clock.System.now().plus(3.toDuration(DurationUnit.DAYS))
        .toLocalDateTime(TimeZone.currentSystemDefault())
)

@Preview
@Composable
fun AdvertisementComponent(
    modifier: Modifier = Modifier,
    advertisement: Advertisement = defaultProps
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .composed { modifier },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Circle(modifier = Modifier.padding(end = 10.dp))
        Text(
            text = advertisement.content,
            style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colors.secondary)
        )
    }
}

@Composable
private fun Circle(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .composed { modifier }
            .size(5.dp)
            .clip(CircleShape)
            .background(if (MaterialTheme.colors.isLight) MaterialTheme.colors.lightPrimary else MaterialTheme.colors.surface)
    )
}