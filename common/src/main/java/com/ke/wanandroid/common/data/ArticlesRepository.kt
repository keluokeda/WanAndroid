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

    /**
     * 获取首页文章
     */
    suspend fun getHomeArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取广场文章
     */
    suspend fun getSquareArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取问答
     */
    suspend fun getAnswers(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取项目文章
     */
    suspend fun getProjectArticles(
        index: Int,
        id: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取公众号文章
     */
    suspend fun getBlogArticles(
        index: Int,
        id: Int,
        keyword: String?
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>
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

    override suspend fun getHomeArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getHomeArticleList(index)
    }

    override suspend fun getSquareArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getSquareArticles(index)
    }

    override suspend fun getAnswers(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getAnswers(index)
    }

    override suspend fun getProjectArticles(
        index: Int,
        id: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getProjectArticles(index, id)
    }

    override suspend fun getBlogArticles(
        index: Int,
        id: Int,
        keyword: String?
    ): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getBlogArticles(id, index, keyword)
    }
}