package com.ke.wanandroid.system.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import javax.inject.Inject
import javax.inject.Singleton

interface SystemRepository {

    suspend fun getArticles(
        page: Int,
        cid: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>
}

@Singleton
class SystemRepositoryImpl @Inject constructor(private val wanApiService: WanApiService) :
    SystemRepository {
    override suspend fun getArticles(
        page: Int,
        cid: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getSystemArticleList(page, cid)
    }

}