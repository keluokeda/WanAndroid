package com.ke.wanandroid.ui.home

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.*
import javax.inject.Inject
import com.ke.mvvm.base.data.Result

class HomeRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any,WanArticleResponse>() {

    suspend fun getBannerData(): Result<WanBaseListResponse<WanBannerResponse>> {
        return try {
            Result.Success(wanApiService.getBanner())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }



    override suspend fun getDataList(index: Int,params:Any): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(wanApiService.getArticleList(index).data?.datas ?: emptyList())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}