package com.ke.wanandroid.settings.nightmode

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath

@Route(path = PagePath.NIGHT_MODE_SETTINGS)
class NightModeActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = NightModeFragment()

}