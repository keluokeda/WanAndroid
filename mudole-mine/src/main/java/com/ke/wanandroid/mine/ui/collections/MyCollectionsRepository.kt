package com.ke.wanandroid.mine.ui.collections

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class MyCollectionsRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {
    override suspend fun getDataList(index: Int, params: Any): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(wanApiService.getUserCollectionArticles(index).requireData().datas)
        } catch (e: Exception) {
            Result.Error(e)
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