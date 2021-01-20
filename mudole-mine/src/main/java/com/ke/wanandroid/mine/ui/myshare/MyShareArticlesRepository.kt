package com.ke.wanandroid.mine.ui.myshare

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class MyShareArticlesRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            val response = wanApiService.getMySharedArticles(index)
            WanBaseResponse(
                response.errorCode,
                response.errorMsg,
                response.data?.shareArticles
            )
        }
    }

    suspend fun deleteArticle(articleResponse: WanArticleResponse): Result<WanBaseResponse<Any>> {
        return try {
            Result.Success(wanApiService.deleteMyShareArticle(articleResponse.id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}