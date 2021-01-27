package com.ke.wanandroid.mine.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanCoinResponse
import com.ke.wanandroid.api.response.WanListResponse
import com.ke.wanandroid.api.response.WanUserInfoResponse
import javax.inject.Inject
import javax.inject.Singleton

interface CoinRepository {

    /**
     * 获取用户积分
     */
    suspend fun getUserCoinCount(): WanBaseResponse<Int>

    /**
     * 获取积分记录
     */
    suspend fun getCoinRecords(index: Int): WanBaseResponse<WanListResponse<WanCoinResponse>>

    /**
     * 获取积分排行榜
     */
    suspend fun getCoinRank(index: Int): WanBaseResponse<WanListResponse<WanUserInfoResponse>>


}

@Singleton
class CoinRepositoryImpl @Inject constructor(private val wanApiService: WanApiService) :
    CoinRepository {
    override suspend fun getUserCoinCount(): WanBaseResponse<Int> {
        return wanApiService.getUserCoinCount()
    }

    override suspend fun getCoinRecords(index: Int): WanBaseResponse<WanListResponse<WanCoinResponse>> {
        return wanApiService.getCoinList(index)
    }

    override suspend fun getCoinRank(index: Int): WanBaseResponse<WanListResponse<WanUserInfoResponse>> {
        return wanApiService.getCoinRank(index)
    }


}