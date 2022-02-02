package epos_next.app.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import epos_next.app.android.components.*
import epos_next.app.android.navigation.MainNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val navController = rememberAnimatedNavController()
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var currentBottomSheet by remember { mutableStateOf<MainBottomSheetScreen?>(null) }
    val scope = rememberCoroutineScope()

    fun closeSheet() = scope.launch { bottomSheetState.bottomSheetState.collapse() }
    fun openSheet(screen: MainBottomSheetScreen) = scope.launch {
        currentBottomSheet = screen
        bottomSheetState.bottomSheetState.expand()
    }

    if (bottomSheetState.bottomSheetState.isCollapsed) currentBottomSheet = null

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        scaffoldState = bottomSheetState,
        sheetContent = {
            currentBottomSheet?.let { currentSheet -> MainSheetLayout(currentSheet, ::closeSheet) }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavBar(navController)
                BarSheetShading(currentBottomSheet != null)
            }
        ) {
            MainNavGraph(navController, ::openSheet)
            SheetShading(currentBottomSheet != null, ::closeSheet)
        }


    }
}