package com.ke.wanandroid.ui.project

import com.ke.mvvm.base.data.ListResult
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class ProjectArticlesRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Int>(wanApiService) {
    override suspend fun getListResult(index: Int, params: Int): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            wanApiService.getProjectArticles(index, params)
        }
    }
}