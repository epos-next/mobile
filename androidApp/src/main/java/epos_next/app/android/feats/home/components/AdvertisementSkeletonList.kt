package epos_next.app.android.feats.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdvertisementSkeletonList(count: Int = 3) {
    Column(modifier = Modifier.fillMaxWidth()) {
        repeat(count) {
            Spacer(modifier = Modifier.height(10.dp))
            AdvertisementSkeleton()
        }
    }
}