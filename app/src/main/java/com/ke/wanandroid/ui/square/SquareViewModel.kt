package com.ke.wanandroid.ui.square

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.domain.square.GetSquareArticlesUseCase

class SquareViewModel @ViewModelInject constructor(
    getSquareArticlesUseCase: GetSquareArticlesUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseArticleListViewModel<Unit>(
        getSquareArticlesUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase,
        addToLaterReadListUseCase
    ) {
    override val parameters: Unit
        get() = Unit


}