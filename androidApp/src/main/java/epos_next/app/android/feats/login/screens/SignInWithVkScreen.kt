package epos_next.app.android.feats.login.screens

import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import epos_next.app.models.VkCookies

@Composable
fun SignInWithVkScreen(navController: NavHostController) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    )

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            WebView(context).apply {
                webViewClient = SignInWithVkWebViewClient(
                    onSuccess = {
                        Log.d("Cookies", it.toString())
                        navController.popBackStack();
                    },
                    onFailure = {},
                )

                loadUrl("https://cabinet.permkrai.ru/login_from_vk")
            }
        }
    )
}

private class SignInWithVkWebViewClient(
    val onSuccess: (VkCookies) -> Unit,
    val onFailure: (Throwable) -> Unit,
) : WebViewClient() {

    private fun extractVkCookies(): VkCookies {
        val cookieManager = CookieManager.getInstance()
        return VkCookies(
            login = cookieManager.getCookie("https://login.vk.com"),
            oauth = cookieManager.getCookie("https://oauth.vk.com"),
            main = cookieManager.getCookie("https://vk.com"),
        )
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url?.contains("/cabinet.permkrai.ru/loginVK") == true) {
            onSuccess(extractVkCookies())
            return false
        }

        view!!.loadUrl(url!!)
        return true
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
        Log.d("redirect", url ?: "unknown")
        if (url?.contains("/cabinet.permkrai.ru/loginVK") == true) {
            onSuccess(extractVkCookies())
        }
    }
}