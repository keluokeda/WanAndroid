package com.ke.wanandroid.mine.ui.coin

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanCoinResponse
import javax.inject.Inject

class MyCoinRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanCoinResponse>() {
    override suspend fun getDataList(index: Int, params: Any): Result<List<WanCoinResponse>> {
        return try {
            Result.Success(wanApiService.getCoinList(index).data!!.datas)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getCoinCount() = try {
        Result.Success(wanApiService.getUserCoinCount())
    } catch (e: Exception) {
        Result.Error(e)
    }
}