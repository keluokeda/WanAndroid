package com.ke.wanandroid.mine.ui.coinrank

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.getListResultFromWanResponse
import javax.inject.Inject

class CoinRankRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanUserInfoResponse>() {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanUserInfoResponse> {
        return getListResultFromWanResponse { wanApiService.getCoinRank(index) }
    }
}