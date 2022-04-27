package epos_next.app.android.feats.login.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import epos_next.app.android.R
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.navigation.Routes

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SignInWithVkButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val background = if (MaterialTheme.colors.isLight) Color(0xFF0077FF)
    else MaterialTheme.colors.surface

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable {
                navController.navigate(Routes.Login.vk)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon()
        Spacer(modifier = Modifier.width(10.dp))
        ButtonText()
    }
}

@Composable
private fun Icon() {
    Image(
        painter = painterResource(id = R.drawable.vk),
        contentDescription = "vk icon",
        modifier = Modifier.size(24.dp)
    )
}

@Composable
private fun ButtonText() {
    Text(
        "Войти через VK",
        fontSize = 18.sp,
        color = Color.White,
    )
}

@Composable
@Preview
private fun PreviewLight() {
    val navHostController = rememberNavController()

    ApplicationTheme(darkTheme = false) {
        Box(modifier = Modifier.width(365.dp)) {
            SignInWithVkButton(navHostController)
        }
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    val navHostController = rememberNavController()

    ApplicationTheme(darkTheme = false) {
        Box(modifier = Modifier.width(365.dp)) {
            SignInWithVkButton(navHostController)
        }
    }
}

@Composable
@Preview
private fun PreviewDark() {
    val navHostController = rememberNavController()

    ApplicationTheme(darkTheme = true) {
        Box(modifier = Modifier.width(365.dp)) {
            SignInWithVkButton(navHostController)
        }
    }
}