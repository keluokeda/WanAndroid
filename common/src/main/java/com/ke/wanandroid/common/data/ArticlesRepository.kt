package com.ke.wanandroid.common.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import javax.inject.Inject

interface ArticlesRepository {
    /**
     * 获取用户分享的文章
     */
    suspend fun getUserSharedArticles(
        userId: Int,
        index: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取我分享的文章
     */
    suspend fun getMySharedArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 删除已分享的文章
     */
    suspend fun deleteSharedArticle(articleId: Int): WanBaseResponse<Any>

    /**
     * 分享文章
     */
    suspend fun shareArticle(title: String, link: String): WanBaseResponse<Any>
}

class ArticlesRepositoryImpl @Inject constructor(private val wanApiService: WanApiService) :
    ArticlesRepository {
    override suspend fun getUserSharedArticles(
        userId: Int,
        index: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        val response = wanApiService.getUserSharedArticles(userId, index)

        return WanBaseResponse(response.errorCode, response.errorMsg, response.data?.shareArticles)
    }

    override suspend fun getMySharedArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        val response = wanApiService.getMySharedArticles(index)

        return WanBaseResponse(response.errorCode, response.errorMsg, response.data?.shareArticles)
    }

    override suspend fun deleteSharedArticle(articleId: Int): WanBaseResponse<Any> {
        return wanApiService.deleteMyShareArticle(articleId)
    }

    override suspend fun shareArticle(title: String, link: String): WanBaseResponse<Any> {
        return wanApiService.shareArticle(title, link)
    }
}