package epos_next.app.android.feats.version.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import epos_next.app.android.R
import epos_next.app.android.components.PrimaryButton
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.android.lib.noRippleClickable
import epos_next.app.utils.playMarketAppMarketUrl
import epos_next.app.utils.playMarketAppUrl

@Composable
fun MajorUpdateScreen(navController: NavHostController) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Spacer(modifier = Modifier.weight(1f))
        Icon()
        Title()
        Subtitle("v1.0.1")
        Description()
        PrimaryButton(
            text = "Обновить",
            onClick = {
                try {
                    uriHandler.openUri(playMarketAppMarketUrl)
                } catch (e: Throwable) {
                    uriHandler.openUri(playMarketAppUrl)
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        SkipButton(navController)
    }
}

@Composable
private fun Icon() {
    val painter = painterResource(id = R.drawable.rocket_icon)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.contrast.copy(alpha = 0.07f))
            .padding(15.dp)
    )
}

@Composable
private fun Title() {
    Text(
        modifier = Modifier.padding(top = 30.dp, bottom = 20.dp),
        text = "Доступна новая версия!",
        style = TextStyle(
            color = MaterialTheme.colors.textPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
    )
}

@Composable
private fun Subtitle(version: String) {
    val text = "Доступно новое обновление $version"
    val builder = AnnotatedString.Builder()
    builder.append(text)
    builder.addStyle(
        SpanStyle(color = MaterialTheme.colors.contrast),
        26,
        26 + version.length
    )
    val annotatedText = builder.toAnnotatedString()

    Text(
        modifier = Modifier.padding(bottom = 7.dp),
        text = annotatedText,
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontSize = 14.sp,
            lineHeight = 25.sp,
        ),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Description() {
    Text(
        modifier = Modifier.padding(bottom = 25.dp),
        text = "Пожалуйста, обновите Эпос Next. " +
                "Эта версия не стабильна и не рекомендуется к использованию",
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontSize = 14.sp,
            lineHeight = 25.sp,
        ),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SkipButton(navController: NavHostController) {
    Text(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .noRippleClickable {
                navController.popBackStack()
            },
        text = "Проигнорировать",
        style = TextStyle(
            color = MaterialTheme.colors.lightPrimary,
            fontSize = 14.sp,
        ),
        textAlign = TextAlign.Center,
    )

}