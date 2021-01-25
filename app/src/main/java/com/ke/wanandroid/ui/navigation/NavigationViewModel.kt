package com.ke.wanandroid.ui.navigation

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.category.BaseCategoryViewModel

class NavigationViewModel @ViewModelInject constructor(private val navigationRepository: NavigationRepository) :
    BaseCategoryViewModel(navigationRepository) {

    fun start() {
        loadTopicList()
    }
}