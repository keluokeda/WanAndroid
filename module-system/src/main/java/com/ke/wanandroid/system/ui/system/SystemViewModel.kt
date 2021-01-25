package com.ke.wanandroid.system.ui.system

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.category.BaseCategoryViewModel

class SystemViewModel @ViewModelInject constructor(systemRepository: SystemRepository) :
    BaseCategoryViewModel(systemRepository) {



    init {
        loadTopicList()
    }
}