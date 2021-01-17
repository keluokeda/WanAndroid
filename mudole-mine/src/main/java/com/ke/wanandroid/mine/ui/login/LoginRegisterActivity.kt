package com.ke.wanandroid.mine.ui.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseActivity
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.mine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.LOGIN)
class LoginRegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, LoginRegisterFragment()).commit()
        }
    }
}