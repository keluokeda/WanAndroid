package com.ke.wanandroid.system.ui.system

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.category.BaseCategoryViewModel
import com.ke.wanandroid.system.domain.GetSystemCategoryUseCase

class SystemViewModel @ViewModelInject constructor(getSystemCategoryUseCase: GetSystemCategoryUseCase) :
    BaseCategoryViewModel(getSystemCategoryUseCase) {



    init {
        loadTopicList()
    }
}