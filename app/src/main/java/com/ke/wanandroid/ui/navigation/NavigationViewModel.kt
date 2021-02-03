package com.ke.wanandroid.ui.navigation

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.category.BaseCategoryViewModel
import com.ke.wanandroid.domain.navigation.GetNavigationTreeUseCase

class NavigationViewModel @ViewModelInject constructor(getNavigationTreeUseCase: GetNavigationTreeUseCase) :
    BaseCategoryViewModel(getNavigationTreeUseCase) {

    fun start() {
        loadTopicList()
    }
}