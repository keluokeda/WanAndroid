package com.ke.wanandroid.officialaccount.ui.officialaccounts

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.wanandroid.common.ui.tab.BaseTabViewModel
import com.ke.wanandroid.officialaccount.domain.article.GetTopicsUseCase
import com.ke.wanandroid.officialaccount.domain.article.LoadTopicsUseCase


class OfficialAccountsViewModel @ViewModelInject constructor(
    getTopicsUseCase: GetTopicsUseCase,
    loadTopicsUseCase: LoadTopicsUseCase
) :
    BaseTabViewModel(getTopicsUseCase, loadTopicsUseCase) {


}