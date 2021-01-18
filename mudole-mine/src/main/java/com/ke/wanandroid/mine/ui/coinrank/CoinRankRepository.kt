package com.ke.wanandroid.mine.ui.coinrank

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanUserInfoResponse
import javax.inject.Inject

class CoinRankRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanUserInfoResponse>() {
    override suspend fun getDataList(index: Int, params: Any): Result<List<WanUserInfoResponse>> {
        return try {
            Result.Success(wanApiService.getCoinRank(index).data!!.datas)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}