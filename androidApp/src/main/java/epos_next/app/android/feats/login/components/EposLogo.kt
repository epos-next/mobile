package epos_next.app.android.feats.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.R;


@Preview
@Composable
fun EposLogo() {
    Box(
        modifier = Modifier
            .height(65.dp)
            .width(65.dp)
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Epos logo",
            contentScale = ContentScale.FillHeight,
        )
    }
}