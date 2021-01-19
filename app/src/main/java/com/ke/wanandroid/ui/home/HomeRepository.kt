package com.ke.wanandroid.ui.home

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBannerResponse
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class HomeRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {

    suspend fun getBannerData(): Result<WanBaseListResponse<WanBannerResponse>> {
        return try {
            Result.Success(wanApiService.getBanner())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


    override suspend fun getDataList(index: Int, params: Any): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(wanApiService.getArticleList(index).data?.datas ?: emptyList())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}