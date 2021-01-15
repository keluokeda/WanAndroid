package com.ke.wanandroid.officialaccount.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse

class ArticleListViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val articleListRepository: ArticleListRepository
) : BaseDataListViewModel<WanArticleResponse>(
    articleListRepository
) {

    var keyword: String? = null
        set(value) {
            field = value
            articleListRepository.keyword = value
            loadData(true)
        }

    init {
        articleListRepository.id = savedStateHandle.get<Int>("id") ?: 0

        keyword = null

    }


}