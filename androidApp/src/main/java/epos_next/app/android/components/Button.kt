package epos_next.app.android.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.R

sealed class ButtonState {
    object Idle : ButtonState()
    object Loading : ButtonState()
    object Done : ButtonState()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    disabled: Boolean = false,
    state: ButtonState = ButtonState.Idle,
) {
    val background by animateColorAsState(
        if (disabled) MaterialTheme.colors.lightPrimary else MaterialTheme.colors.contrast
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color(255, 255, 255, 0x33))
            ) {
                if (!disabled && state == ButtonState.Idle) onClick()
            }
    ) {

        AnimatedContent(
            targetState = state,
            modifier = Modifier
                .align(Alignment.Center),
            transitionSpec = {
                if (this.initialState is ButtonState.Idle || this.targetState is ButtonState.Idle)
                    fadeIn() with fadeOut()
                else {
                    if (initialState is ButtonState.Loading) {
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            }
        ) { state ->
            when (state) {
                is ButtonState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(18.dp),
                    strokeWidth = 3.dp,
                    color = Color.White
                )
                is ButtonState.Done -> Image(
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                is ButtonState.Idle -> Text(
                    text = text,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White,
                )
            }

        }
    }
}