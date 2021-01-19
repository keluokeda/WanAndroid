package com.ke.wanandroid.common.model

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import java.lang.Exception

abstract class BaseArticleListRepository<Params>(private val wanApiService: WanApiService) :
    BaseDataListRepository<Params, WanArticleResponse>() {

    /**
     * 收藏文章
     */
    suspend fun collectArticle(wanArticleResponse: WanArticleResponse): Result<WanBaseResponse<Any>> {
        return try {
            Result.Success(wanApiService.collectArticle(wanArticleResponse.id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }



    /**
     * 取消收藏
     */
    open suspend fun cancelCollectArticle(wanArticleResponse: WanArticleResponse): Result<WanBaseResponse<Any>> {
        return try {
            Result.Success(wanApiService.cancelCollectArticle(wanArticleResponse.id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}