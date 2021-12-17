package epos_next.app.android.feats.login.parts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.login.components.EposLogo
import epos_next.app.android.feats.login.components.EposName

@Composable
fun LogoAndName() {
    EposLogo()
    Spacer(modifier = Modifier.height(10.dp))
    EposName()
    Spacer(modifier = Modifier.height(16.dp))
}