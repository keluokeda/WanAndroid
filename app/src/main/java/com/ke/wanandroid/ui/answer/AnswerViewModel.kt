package com.ke.wanandroid.ui.answer

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.BaseArticleListViewModel

class AnswerViewModel @ViewModelInject constructor(private val answerRepository: AnswerRepository) :
    BaseArticleListViewModel<Any>(answerRepository) {
    override val params: Any
        get() = 0
}