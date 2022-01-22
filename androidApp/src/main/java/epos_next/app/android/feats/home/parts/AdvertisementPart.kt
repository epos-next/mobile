package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.home.components.AdvertisementComponent
import epos_next.app.android.feats.home.components.AdvertisementSkeletonList
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.android.feats.home.components.TitleWithCreateButton
import org.koin.androidx.compose.getViewModel

@Composable
fun AdvertisementPart() {
    TitleWithCreateButton(text = "Объявления", modifier = Modifier.padding(top = 25.dp))

    AdvertisementSkeletonList()

//    when (state) {
//        is AdsState.IdleState -> {
//
//            for (ad in state.advertisements) {
//                AdvertisementComponent(
//                    modifier = Modifier.padding(top = 10.dp),
//                    advertisement = ad,
//                )
//            }
//        }
//        is AdsState.ErrorState -> TextError(Modifier.padding(top = 10.dp), state.error)
//        is AdsState.LoadingState -> AdvertisementSkeletonList()
//    }
}