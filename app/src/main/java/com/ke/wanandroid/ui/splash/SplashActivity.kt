package com.ke.wanandroid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.MainActivity
import com.ke.wanandroid.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        startActivity(Intent(this, MainActivity::class.java))
        ARouter.getInstance().build("/app/main").navigation()
    }
}