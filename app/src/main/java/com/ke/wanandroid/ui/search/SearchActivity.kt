package com.ke.wanandroid.ui.search

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.APP_SEARCH)
class SearchActivity : BaseFragmentActivity() {


    override val fragment: Fragment
        get() = SearchHistoryFragment()


    companion object {
        const val EXTRA_KEY_KEYWORD = "EXTRA_KEY_KEYWORD"
    }
}