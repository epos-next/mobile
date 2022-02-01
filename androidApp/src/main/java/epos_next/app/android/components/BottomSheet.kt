package epos_next.app.android.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

sealed class MainBottomSheetScreen {
    object Advertisement : MainBottomSheetScreen()
    object ControlWork : MainBottomSheetScreen()
}

@Composable
private fun BottomSheetWithCloseDialog(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .width(75.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.background)
        )
        Column(
            modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                )
                .background(MaterialTheme.colors.background)
                .padding(
                    horizontal = 20.dp,
                    vertical = 30.dp,
                )
        ) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    Box(Modifier.background(Color.Black)) {
        BottomSheetWithCloseDialog {
            Text(
                "Test", modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun SheetShading(show: Boolean, close: () -> Unit) {

    AnimatedVisibility(
        visible = show,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3F))
        ) {
            Button(
                onClick = { close() },
                modifier = Modifier
                    .alpha(0.0F)
                    .fillMaxSize()
            ) {}
        }
    }
}

@Composable
fun BarSheetShading(show: Boolean) {
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.3F))
        )
    }
}

@Composable
fun MainSheetLayout(screen: MainBottomSheetScreen) {
    val modifier = Modifier.fillMaxHeight(0.3F)

    BottomSheetWithCloseDialog {
        when (screen) {
            MainBottomSheetScreen.Advertisement -> Text("ads", modifier = modifier)
            MainBottomSheetScreen.ControlWork -> Text("ads", modifier = modifier)
        }
    }
}
