package com.ke.wanandroid.system.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.system.domain.GetSystemArticlesUseCase

class ArticleListViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    getSystemArticlesUseCase: GetSystemArticlesUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseArticleListViewModel<Int>(
        getSystemArticlesUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase,
        addToLaterReadListUseCase
    ) {

    override val startIndex: Int
        get() = 0
    private val id = savedStateHandle.get<WanTopicResponse>(ExtraKey.TOPIC)!!.id


    init {
        loadData(true)
    }

    override val parameters: Int
        get() = id


}