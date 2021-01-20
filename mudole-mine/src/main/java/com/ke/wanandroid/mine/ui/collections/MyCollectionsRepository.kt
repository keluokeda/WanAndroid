package com.ke.wanandroid.mine.ui.collections

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class MyCollectionsRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            wanApiService.getUserCollectionArticles(index)
        }
    }

    override suspend fun cancelCollectArticle(wanArticleResponse: WanArticleResponse): Result<WanBaseResponse<Any>> {
        return try {
            Result.Success(
                wanApiService.cancelCollectArticle(
                    wanArticleResponse.id,
                    wanArticleResponse.originId
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}