package com.ke.wanandroid.ui.project

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ke.wanandroid.common.ui.BaseArticleListViewModel

class ProjectArticlesViewModel @ViewModelInject constructor(
    private val projectArticlesRepository: ProjectArticlesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    BaseArticleListViewModel<Int>(projectArticlesRepository) {
    override val params: Int
        get() = savedStateHandle.get<Int>("id") ?: throw RuntimeException("缺少id")

    fun start() {
        loadData(true)
    }

    init {
    }
}