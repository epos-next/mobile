package epos_next.app.android.feats.profile.screens.about_devs_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

@Composable
fun UriSpan(
    text: String,
    segment: String,
    link: String,
) {
    val uriHandler = LocalUriHandler.current

    val builder = AnnotatedString.Builder()
    builder.append(text)
    val start = text.indexOf(segment)
    val end = start + segment.length
    builder.addStringAnnotation("URL", link, start, end)
    val annotatedText = builder.toAnnotatedString()

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            fontSize = 13.sp,
            color = MaterialTheme.colors.secondary,
            lineHeight = 28.sp,
        ),
        modifier = Modifier.padding(horizontal = 20.dp),
        onClick = {
            annotatedText
                .getStringAnnotations("URL", it, it)
                .firstOrNull()
                ?.let { url ->
                    Firebase.analytics.logEvent("visit_website") {
                        param(FirebaseAnalytics.Param.VALUE, url.item)
                    }
                    uriHandler.openUri(url.item)
                }
        }
    )
}