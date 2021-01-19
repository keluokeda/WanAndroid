package com.ke.wanandroid.mine.ui.collections

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.ui.BaseArticleListViewModel

class MyCollectionsViewModel @ViewModelInject constructor(private val myCollectionsRepository: MyCollectionsRepository) :
    BaseArticleListViewModel<Any>(myCollectionsRepository) {
    override val params: Any
        get() = 0

    override val startIndex: Int
        get() = 0

    init {
        loadData(true)
    }

    override fun onCancelCollectArticleSuccess(article: WanArticleResponse) {
        super.onCancelCollectArticleSuccess(article)
        val list = _dataList.value ?: return
        if (list.isEmpty()) {
            return
        }
        _dataList.value = list - article
    }
}