package com.ke.wanandroid.ui.project

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.domain.project.GetProjectArticlesUseCase

class ProjectArticlesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    getProjectArticlesUseCase: GetProjectArticlesUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseArticleListViewModel<Int>(
        getProjectArticlesUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase,
        addToLaterReadListUseCase
    ) {
    override val parameters: Int
        get() = savedStateHandle.get<Int>("id") ?: throw RuntimeException("缺少id")


}