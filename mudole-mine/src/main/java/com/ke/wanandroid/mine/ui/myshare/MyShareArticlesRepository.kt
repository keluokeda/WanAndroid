package com.ke.wanandroid.mine.ui.myshare

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class MyShareArticlesRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {
    override suspend fun getDataList(index: Int, params: Any): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(
                wanApiService.getMySharedArticles(index).requireData().shareArticles.datas
            )
        } catch (e: Exception) {
            Result.Error(e)
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