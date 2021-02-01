package com.ke.wanandroid.settings.ui.nightmode

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.NIGHT_MODE_SETTINGS)
class NightModeActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = NightModeFragment()

}