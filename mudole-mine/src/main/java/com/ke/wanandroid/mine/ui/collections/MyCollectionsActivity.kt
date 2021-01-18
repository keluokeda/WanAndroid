package com.ke.wanandroid.mine.ui.collections

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.MY_COLLECTIONS)
class MyCollectionsActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = MyCollectionsFragment()
}