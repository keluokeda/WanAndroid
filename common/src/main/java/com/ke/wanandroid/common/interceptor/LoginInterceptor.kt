package com.ke.wanandroid.common.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.common.UserDataStoreProvider
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.data.UserDataStore

@Interceptor(priority = 9, name = "登录拦截器")
class LoginInterceptor : IInterceptor {
    private val list = listOf(
        PagePath.MY_COIN,
        PagePath.MY_COLLECTIONS,
        PagePath.MY_SHARE_ARTICLES
    )

    lateinit var userDataStore: UserDataStore

    override fun init(context: Context) {
        userDataStore = (context.applicationContext as UserDataStoreProvider).store
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {

        if (needIntercept(postcard)) {
            //拦截
            callback.onInterrupt(RuntimeException("需要登录才能访问"))

            //跳转到登录
            ARouter.getInstance().build(PagePath.LOGIN)
                .navigation()
        } else {
            callback.onContinue(postcard)
        }
    }

    private fun needIntercept(postcard: Postcard): Boolean {
        if (userDataStore.isLogin) {
            return false
        }
        return postcard.path in list
    }
}