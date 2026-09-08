package com.example.ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebSandboxView(
    htmlContent: String,
    modifier: Modifier = Modifier,
    onConsoleLog: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                cacheMode = WebSettings.LOAD_NO_CACHE
                allowFileAccess = true
                allowContentAccess = true
                mediaPlaybackRequiresUserGesture = false
            }

            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    consoleMessage?.let {
                        onConsoleLog("${it.messageLevel()}: ${it.message()} (خط ${it.lineNumber()})")
                    }
                    return true
                }

                override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                    // Let the default alert run or handle smoothly
                    result?.confirm()
                    message?.let { onConsoleLog("پیام Alert: $it") }
                    return true
                }
            }

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }
            }
        }
    }

    DisposableEffect(htmlContent) {
        webView.loadDataWithBaseURL(
            "https://tirotir.ir/",
            htmlContent,
            "text/html",
            "UTF-8",
            null
        )
        onDispose { }
    }

    AndroidView(
        factory = { webView },
        modifier = modifier.fillMaxSize().testTag("web_sandbox_view"),
        update = {
            it.loadDataWithBaseURL(
                "https://tirotir.ir/",
                htmlContent,
                "text/html",
                "UTF-8",
                null
            )
        }
    )
}
