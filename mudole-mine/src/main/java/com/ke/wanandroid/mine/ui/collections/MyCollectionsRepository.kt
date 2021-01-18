package com.ke.wanandroid.mine.ui.collections

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import javax.inject.Inject

class MyCollectionsRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Any, WanArticleResponse>() {
    override suspend fun getDataList(index: Int, params: Any): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(wanApiService.getUserCollectionArticles(index).requireData().datas)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}