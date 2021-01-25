package com.ke.wanandroid.common.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import javax.inject.Inject
import javax.inject.Singleton

interface MyCollectionsRepository {

    /**
     * 根据id收藏文章
     */
    suspend fun collectArticle(id: Int): WanBaseResponse<Any>

    /**
     * 根据id取消收藏文章
     */
    suspend fun cancelCollectArticle(articleId: Int): WanBaseResponse<Any>

    /**
     * 获得我收藏的所有的文章
     */
    suspend fun getMyCollectionArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    suspend fun cancelCollectMyArticle(id: Int, originId: Int): WanBaseResponse<Any>
}

@Singleton
class MyCollectionsRepositoryImpl @Inject constructor(private val wanApiService: WanApiService) :
    MyCollectionsRepository {
    override suspend fun collectArticle(id: Int): WanBaseResponse<Any> {
        return wanApiService.collectArticle(id)
    }

    override suspend fun cancelCollectArticle(articleId: Int): WanBaseResponse<Any> {
        return wanApiService.cancelCollectArticle(articleId)
    }

    override suspend fun getMyCollectionArticles(index: Int): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.getUserCollectionArticles(index)
    }

    override suspend fun cancelCollectMyArticle(id: Int, originId: Int): WanBaseResponse<Any> {
        return wanApiService.cancelCollectArticle(id, originId)
    }


}