package com.ke.wanandroid.ui.square

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.BaseArticleListViewModel

class SquareViewModel @ViewModelInject constructor(private val squareRepository: SquareRepository) :
    BaseArticleListViewModel<Any>(squareRepository) {
    override val params: Any
        get() = 0


}