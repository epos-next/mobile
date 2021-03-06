package epos_next.app.android.feats.profile.screens.about_devs_screen

import android.widget.Space
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import epos_next.app.android.BuildConfig
import epos_next.app.android.R
import epos_next.app.android.feats.profile.components.ProfileHeader
import epos_next.app.android.feats.profile.screens.about_devs_screen.components.DeveloperComponent
import epos_next.app.android.feats.profile.screens.about_devs_screen.components.UriSpan
import epos_next.app.android.feats.profile.screens.about_devs_screen.components.VersionText

@Composable
fun AboutDevsScreen(
    rootNavController: NavHostController,
    scrollState: ScrollState,
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 65.dp)
            .background(MaterialTheme.colors.background)
    ) {
        ProfileHeader(
            image = R.drawable.dev_icon_96,
            color = Color(0xFF83D4FC),
            text = "О разработчиках",
            navController = rootNavController,
        )

        DeveloperComponent(
            name = "Ярослав Зотов",
            role = "Создатель",
            image = R.drawable.yaroslav_zotov
        ) {
            Firebase.analytics.logEvent("visit_developer_website") {
                param(FirebaseAnalytics.Param.ITEM_NAME, "Visit zotov.dev")
            }
            uriHandler.openUri("https://zotov.dev")
        }
        Spacer(modifier = Modifier.height(20.dp))

        UriSpan(
            text = "Огромное спасибо Виталию Сухоплечеву и Василию Гайсену за помощь в создании, " +
                    "и grahacaesara за вдохновение в дизайне",
            segment = "grahacaesara",
            link = "https://dribbble.com/shots/6853761-Get-Study-Dashboard-App"
        )
        Spacer(modifier = Modifier.height(20.dp))

        UriSpan(
            text = "Это open source проект. Вы можете найти исходники на нашем гитхабе" +
                    " github.com/epos-next",
            segment = "github.com/epos-next",
            link = "https://github.com/epos-next/"
        )
        Spacer(modifier = Modifier.height(20.dp))


        UriSpan(
            text = "Понравился проект? Вы можете присоединиться к нашей команду! Напишите в телеграм" +
                    " @zotovy",
            segment = "@zotovy",
            link = "https://t.me/zotovy"
        )
        Spacer(modifier = Modifier.height(20.dp))

        VersionText()
    }
}