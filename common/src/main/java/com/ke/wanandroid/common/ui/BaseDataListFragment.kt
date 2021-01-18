package com.ke.wanandroid.common.ui

import com.ke.mvvm.base.ui.BaseDataListFragment
import com.ke.wanandroid.common.R

abstract class BaseDataListFragment(layoutId: Int) : BaseDataListFragment(layoutId) {


    override val emptyLayoutId: Int
        get() = R.layout.layout_list_empty
}