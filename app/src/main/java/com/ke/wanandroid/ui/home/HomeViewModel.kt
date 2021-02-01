package com.ke.wanandroid.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.domain.home.GetHomeArticlesUseCase


class HomeViewModel @ViewModelInject constructor(
    getHomeArticlesUseCase: GetHomeArticlesUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseArticleListViewModel<Unit>(
        getHomeArticlesUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase,
        addToLaterReadListUseCase
    ) {


    override val parameters: Unit
        get() = Unit
    override val startIndex: Int
        get() = 0



    init {
        loadData(true)
    }





}