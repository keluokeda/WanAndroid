package com.ke.wanandroid.mine.ui.collections

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse

class MyCollectionsViewModel @ViewModelInject constructor(private val myCollectionsRepository: MyCollectionsRepository) :
    BaseDataListViewModel<Any, WanArticleResponse>(myCollectionsRepository) {
    override val params: Any
        get() = 0

    override val startIndex: Int
        get() = 0
    init {
        loadData(true)
    }
}