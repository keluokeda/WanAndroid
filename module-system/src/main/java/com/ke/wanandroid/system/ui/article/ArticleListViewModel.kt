package com.ke.wanandroid.system.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanTopicResponse

class ArticleListViewModel @ViewModelInject constructor(
    articleListRepository: ArticleListRepository,
    @Assisted savedStateHandle: SavedStateHandle
) :
    BaseDataListViewModel<Int, WanArticleResponse>(articleListRepository) {

    override val startIndex: Int
        get() = 0
    private val id = savedStateHandle.get<WanTopicResponse>(ArticleListActivity.EXTRA_TOPIC)!!.id


    init {
        loadData(true)
    }

    override val params: Int
        get() = id
}