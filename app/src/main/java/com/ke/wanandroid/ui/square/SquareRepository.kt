package com.ke.wanandroid.ui.square

import com.ke.mvvm.base.data.ListResult
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class SquareRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Any>(wanApiService) {
    override suspend fun getListResult(index: Int, params: Any): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            wanApiService.getSquareArticles(index)
        }
    }
}