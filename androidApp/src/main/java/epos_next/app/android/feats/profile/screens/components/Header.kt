package epos_next.app.android.feats.profile.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import epos_next.app.android.R

@Composable
fun ProfileHeader(
    @DrawableRes image: Int,
    color: Color,
    text: String,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .height(228.dp)
            .fillMaxWidth()
            .background(color)
            .padding(20.dp)
    ) {
        ArrowBack(navController)
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(image)
            Spacer(modifier = Modifier.width(10.dp))
            HeaderText(text)
        }
    }
}

@Composable
private fun ArrowBack(navController: NavController) {
    // animation
    val show = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    val painter = painterResource(id = R.drawable.arrow_back_white)
    val density = LocalDensity.current

    AnimatedVisibility(
        visibleState = show,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Image(
            painter = painter,
            contentDescription = "Back",
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
private fun Icon(@DrawableRes image: Int) {
    val painter = painterResource(id = image)
    Image(painter = painter, contentDescription = null, modifier = Modifier.size(48.dp))
}

@Composable
private fun HeaderText(text: String) {
    // animation
    val show = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = show,
        enter = slideInVertically(
            animationSpec = tween(delayMillis = 250),
            initialOffsetY = { x -> x / 2 }
        ) + fadeIn(animationSpec = tween(delayMillis = 250)),
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}