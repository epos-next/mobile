package epos_next.app.android.feats.version.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.state.dark_mode.DarkModeReducer
import epos_next.app.utils.playMarketAppMarketUrl
import epos_next.app.utils.playMarketAppUrl
import org.koin.androidx.compose.get

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateDialog(open: Boolean, onClose: () -> Unit) {
    if (open) {
        ApplicationTheme {
            Dialog(
                properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = { onClose() }
            ) {
                Body {
                    Title()
                    Subtitle()
                    Row {
                        val uriHandler = LocalUriHandler.current

                        Button(
                            text = "Обновить",
                            color = MaterialTheme.colors.contrast,
                        ) {
                            try {
                                uriHandler.openUri(playMarketAppMarketUrl)
                            } catch (e: Throwable) {
                                uriHandler.openUri(playMarketAppUrl)
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            text = "Не сейчас",
                            color = MaterialTheme.colors.lightPrimary,
                        ) { onClose() }
                    }
                }
            }
        }
    }
}

@Composable
private fun Body(content: @Composable ColumnScope.() -> Unit) {
    val darkModeReducer = get<DarkModeReducer>()
    val isDarkMode by darkModeReducer.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.background)
            .padding(20.dp)

    ) {
        Column(content = content)
    }
}

@Composable
private fun Title() {
    Text(
        text = "Доступно обновление",
        modifier = Modifier.padding(bottom = 5.dp),
        style = TextStyle(
            color = MaterialTheme.colors.textPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.W500,
        ),
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = "Новая версия Эпос Next уже готова к установке ",
        modifier = Modifier.padding(bottom = 10.dp),
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontSize = 13.sp,
        ),
    )
}

@Composable
private fun RowScope.Button(text: String, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(35.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(color)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color(255, 255, 255, 0x33)),
                onClick = onClick
            )
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 13.sp,
            fontWeight = FontWeight.W500,
            color = Color.White,
        )
    }
}