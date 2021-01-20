package com.ke.wanandroid.settings.main

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath

@Route(path = PagePath.SETTINGS)
class SettingsActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = SettingsFragment()
}