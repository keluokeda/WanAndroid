package com.ke.wanandroid.officialaccount.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse

class ArticleListViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val articleListRepository: ArticleListRepository
) : BaseDataListViewModel<Pair<Int, String?>, WanArticleResponse>(
    articleListRepository
) {

    private val id =
        savedStateHandle.get<Int>("id") ?: throw RuntimeException("ArticleListViewModel 需要传入 id")
    var keyword: String? = null
        set(value) {
            field = value
            loadData(true)
        }

    init {

        keyword = null

    }

    override val params: Pair<Int, String?>
        get() = id to keyword


}