package com.ke.wanandroid.mine.ui.sharearticle

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseResponse
import java.lang.Exception
import javax.inject.Inject

class ShareArticleRepository @Inject constructor(private val wanApiService: WanApiService) {

    /**
     * 分享文章
     */
    suspend fun shareArticle(title: String, link: String): Result<WanBaseResponse<Any>> {
        return try {
            Result.Success(wanApiService.shareArticle(title, link))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}