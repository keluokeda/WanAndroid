package com.ke.wanandroid.mine.ui.coin

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanCoinResponse
import com.ke.wanandroid.common.getListResultFromWanResponse
import javax.inject.Inject

class MyCoinRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanCoinResponse>() {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanCoinResponse> {
        return getListResultFromWanResponse {
            wanApiService.getCoinList(index)
        }
    }

    suspend fun getCoinCount() = try {
        Result.Success(wanApiService.getUserCoinCount())
    } catch (e: Exception) {
        Result.Error(e)
    }
}