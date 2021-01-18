package com.ke.wanandroid.h5

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.H5_DEFAULT)
class DefaultWebViewActivity : WebViewActivity() {

    @Autowired(name = ExtraKey.URL)
    lateinit var target: String


    override val url: String
        get() = target

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)

    }
}