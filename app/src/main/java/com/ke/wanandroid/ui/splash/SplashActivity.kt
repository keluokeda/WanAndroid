package com.ke.wanandroid.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.MainActivity
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        startActivity(Intent(this, MainActivity::class.java))
        ARouter.getInstance().build(PagePath.MAIN).navigation()
        finish()
    }
}