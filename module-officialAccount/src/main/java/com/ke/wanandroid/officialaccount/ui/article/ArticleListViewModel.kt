package com.ke.wanandroid.officialaccount.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.officialaccount.domain.article.GetBlogArticlesUseCase

class ArticleListViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    getBlogArticlesUseCase: GetBlogArticlesUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) : BaseArticleListViewModel<Pair<Int, String?>>(
    getBlogArticlesUseCase,
    collectArticleUseCase,
    cancelCollectArticleUseCase,
    addToLaterReadListUseCase
) {

    private val id =
        savedStateHandle.get<Int>("id") ?: throw RuntimeException("ArticleListViewModel 需要传入 id")
    var keyword: String? = null
        set(value) {
            field = value
            loadData(true)
        }


    override val parameters: Pair<Int, String?>
        get() = id to keyword


}