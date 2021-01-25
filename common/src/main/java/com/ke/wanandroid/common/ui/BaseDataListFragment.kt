package com.ke.wanandroid.common.ui

import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreFragment
import com.ke.wanandroid.common.R

abstract class BaseDataListFragment(layoutId: Int) : BaseRefreshAndLoadMoreFragment(layoutId) {


    override val emptyLayoutId: Int
        get() = R.layout.layout_list_empty
}