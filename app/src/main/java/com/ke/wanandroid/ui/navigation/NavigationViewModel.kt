package com.ke.wanandroid.ui.navigation

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.category.BaseCategoryViewModel
import com.ke.wanandroid.domain.navigation.GetNavigationCategoryUseCase

class NavigationViewModel @ViewModelInject constructor(getNavigationCategoryUseCase: GetNavigationCategoryUseCase) :
    BaseCategoryViewModel(getNavigationCategoryUseCase) {

    fun start() {
        loadTopicList()
    }
}