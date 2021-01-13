package com.ke.wanandroid.ui.home

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.*
import com.ke.wanandroid.common.entity.Result
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(private val wanApiService: WanApiService) {

    suspend fun getBannerData(): Result<WanBaseListResponse<WanBannerResponse>> {
        return try {
            Result.Success(wanApiService.getBanner())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getArticleList(index: Int): Result<WanBaseResponse<WanListResponse<WanArticleResponse>>> {
        return try {
            Result.Success(wanApiService.getArticleList(index))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}