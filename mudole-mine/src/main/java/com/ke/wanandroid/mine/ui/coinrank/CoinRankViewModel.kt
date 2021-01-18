package com.ke.wanandroid.mine.ui.coinrank

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanUserInfoResponse

class CoinRankViewModel @ViewModelInject constructor(private val coinRankRepository: CoinRankRepository) :
    BaseDataListViewModel<Any, WanUserInfoResponse>(coinRankRepository) {
    override val params: Any
        get() = 0

    init {
        loadData(true)
    }
}