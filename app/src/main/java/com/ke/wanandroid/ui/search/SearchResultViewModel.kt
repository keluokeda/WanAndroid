package com.ke.wanandroid.ui.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.domain.search.GetSearchResultUseCase
import com.ke.wanandroid.domain.search.InsertHistoryUseCase
import kotlinx.coroutines.launch

class SearchResultViewModel @ViewModelInject constructor(
    getSearchResultUseCase: GetSearchResultUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase,
    private val insertHistoryUseCase: InsertHistoryUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseArticleListViewModel<String>(
    getSearchResultUseCase,
    collectArticleUseCase,
    cancelCollectArticleUseCase,
    addToLaterReadListUseCase
) {
    public override val parameters: String
        get() = savedStateHandle.get<String>(SearchActivity.EXTRA_KEY_KEYWORD)
            ?: throw RuntimeException("需要传入 keyword")

    override val startIndex: Int
        get() = 0

    init {
        loadData(true)

        viewModelScope.launch {
            insertHistoryUseCase(parameters)
        }
    }
}