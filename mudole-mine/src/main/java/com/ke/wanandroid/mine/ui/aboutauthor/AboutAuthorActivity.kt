package com.ke.wanandroid.mine.ui.aboutauthor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.MaterialToolbar
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.mine.R

@Route(path = PagePath.ABOUT_AUTHOR)
class AboutAuthorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_activity_about_auhtor)

        findViewById<View>(R.id.github_title).setOnClickListener {
            toWebView("https://github.com/keluokeda")
        }

        findViewById<View>(R.id.jiansu_title).setOnClickListener {
            toWebView("https://www.jianshu.com/u/6b7a61f5f603")
        }

        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun toWebView(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}