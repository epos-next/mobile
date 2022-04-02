package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.MainBottomSheetScreen
import epos_next.app.android.feats.home.components.AdvertisementComponent
import epos_next.app.android.feats.home.components.AdvertisementSkeletonList
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.android.feats.home.components.TitleWithCreateButton
import epos_next.app.android.lib.collectAsState
import epos_next.app.state.advertisements.AdvertisementsReducer
import epos_next.app.state.advertisements.AdvertisementsState
import org.koin.androidx.compose.get

@Composable
fun AdvertisementPart(openBottomSheet: (MainBottomSheetScreen) -> Unit) {
    val reducer = get<AdvertisementsReducer>()

    TitleWithCreateButton(
        text = "Объявления",
        modifier = Modifier.padding(top = 25.dp),
        onTap = { openBottomSheet(MainBottomSheetScreen.Advertisement) },
    )

    when (val state = reducer.collectAsState()) {
        is AdvertisementsState.Idle -> {
            for (ad in state.advertisements) {
                AdvertisementComponent(
                    modifier = Modifier.padding(top = 10.dp),
                    advertisement = ad,
                )
            }
        }
        is AdvertisementsState.Error -> TextError(Modifier.padding(top = 10.dp), state.message)
        is AdvertisementsState.Loading -> AdvertisementSkeletonList()
    }
}