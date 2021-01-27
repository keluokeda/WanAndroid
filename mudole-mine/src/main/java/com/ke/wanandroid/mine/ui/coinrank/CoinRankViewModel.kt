package com.ke.wanandroid.mine.ui.coinrank

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.mine.domain.coin.GetCoinRankUseCase

class CoinRankViewModel @ViewModelInject constructor(getCoinRankUseCase: GetCoinRankUseCase) :
    BaseRefreshAndLoadMoreViewModel<Unit, WanUserInfoResponse>(getCoinRankUseCase) {
    override val parameters: Unit
        get() = Unit

    init {
        loadData(true)
    }
}