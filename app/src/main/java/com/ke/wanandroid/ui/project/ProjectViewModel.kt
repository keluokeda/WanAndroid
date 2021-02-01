package com.ke.wanandroid.ui.project

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.tab.BaseTabViewModel
import com.ke.wanandroid.domain.project.GetTopicsUseCase
import com.ke.wanandroid.domain.project.LoadTopicsUseCase


class ProjectViewModel @ViewModelInject constructor(
    getTopicsUseCase: GetTopicsUseCase,
    loadTopicsUseCase: LoadTopicsUseCase
) :
    BaseTabViewModel(getTopicsUseCase, loadTopicsUseCase) {


}