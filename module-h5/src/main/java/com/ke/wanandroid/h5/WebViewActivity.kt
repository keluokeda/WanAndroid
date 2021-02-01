package com.ke.wanandroid.h5

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.CallSuper
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseActivity
import com.ke.wanandroid.h5.databinding.ActivityWebViewBinding

abstract class WebViewActivity : BaseActivity() {


    protected val binding: ActivityWebViewBinding by viewbind()
    abstract val url: String

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.webView.webViewClient = object : WebViewClient() {

        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.webView.settings.apply {
            javaScriptEnabled = true
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String) {
                super.onReceivedTitle(view, title)
                binding.toolbar.title = title
            }
        }
        binding.webView.loadUrl(url)
    }
}