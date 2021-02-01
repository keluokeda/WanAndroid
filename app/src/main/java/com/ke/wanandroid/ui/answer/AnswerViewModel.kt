package com.ke.wanandroid.ui.answer

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.domain.answer.GetAnswersUseCase

class AnswerViewModel @ViewModelInject constructor(
    getAnswersUseCase: GetAnswersUseCase,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseArticleListViewModel<Unit>(
        getAnswersUseCase,
        collectArticleUseCase,
        cancelCollectArticleUseCase,
        addToLaterReadListUseCase
    ) {
    override val parameters: Unit
        get() = Unit
}