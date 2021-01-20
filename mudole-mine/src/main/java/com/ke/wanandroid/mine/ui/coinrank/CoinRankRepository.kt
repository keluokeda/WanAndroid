package com.ke.wanandroid.mine.ui.coinrank

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.getListResultWithTry
import javax.inject.Inject

class CoinRankRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanUserInfoResponse>() {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanUserInfoResponse> {
        return getListResultWithTry { wanApiService.getCoinRank(index) }
    }
}