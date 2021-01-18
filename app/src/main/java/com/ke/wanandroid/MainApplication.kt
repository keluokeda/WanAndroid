package com.ke.wanandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.common.UserDataStoreProvider
import com.ke.wanandroid.common.data.UserDataStore
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), UserDataStoreProvider {

    @Inject
    lateinit var userDataStore: UserDataStore

    override val store: UserDataStore
        get() = userDataStore

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

        Logger.addLogAdapter(AndroidLogAdapter())
    }
}