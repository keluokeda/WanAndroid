package com.ke.wanandroid.system.ui.article

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class ArticleListRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Int>(wanApiService) {

    override suspend fun getListResult(index: Int, params: Int): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            wanApiService.getSystemArticleList(index, params)
        }
    }
}