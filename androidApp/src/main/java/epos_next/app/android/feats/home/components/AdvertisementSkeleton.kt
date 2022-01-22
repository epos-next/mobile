package epos_next.app.android.feats.home.components

import androidx.compose.runtime.Composable
import epos_next.app.android.components.TextSkeleton
import kotlin.random.Random

@Composable
fun AdvertisementSkeleton() {
    TextSkeleton(widthMultiplier = generateWidthMultiplier())
}

private fun generateWidthMultiplier(): Float =
    Random.nextDouble(from = 0.6, until = 0.8).toFloat()