package com.ke.wanandroid.mine.ui.collections

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.mine.domain.collection.CancelCollectMyArticleUseCase
import com.ke.wanandroid.mine.domain.collection.GetAllCollectionsUseCase
import kotlinx.coroutines.launch

open class MyCollectionsViewModel @ViewModelInject constructor(
    getAllCollectionsUseCase: GetAllCollectionsUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    private val cancelCollectMyArticleUseCase: CancelCollectMyArticleUseCase
) :
    BaseArticleListViewModel<Unit>(
        getAllCollectionsUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase
    ) {

    override val parameters: Unit
        get() = Unit

    override val startIndex: Int
        get() = 0

    init {
        loadData(true)
    }

    override fun cancelCollectArticle(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            cancelCollectArticleInner(
                wanArticleResponse,
                wanArticleResponse.id to wanArticleResponse.originId,
                cancelCollectMyArticleUseCase
            )
            if (!wanArticleResponse.collect) {
                //移除数据
                _dataList.value = (_dataList.value ?: emptyList()) - wanArticleResponse
            }
        }


    }
}