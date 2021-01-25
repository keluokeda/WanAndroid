package com.ke.wanandroid.system.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.ui.BaseArticleListViewModel

class ArticleListViewModel @ViewModelInject constructor(
    articleListRepository: ArticleListRepository,
    @Assisted savedStateHandle: SavedStateHandle
) :
    BaseArticleListViewModel<Int>(articleListRepository) {

    override val startIndex: Int
        get() = 0
    private val id = savedStateHandle.get<WanTopicResponse>(ExtraKey.TOPIC)!!.id


    init {
        loadData(true)
    }

    override val params: Int
        get() = id
}