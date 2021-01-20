package com.ke.wanandroid.mine.ui.sharedarticles

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject

class UserSharedArticlesRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Int>(wanApiService) {
    override suspend fun getListResult(index: Int, params: Int): ListResult<WanArticleResponse> {
        return getListResultWithTry {
            val response = wanApiService.getUserSharedArticles(params, index)
            WanBaseResponse(
                response.errorCode,
                response.errorMsg,
                response.data?.shareArticles
            )
        }
    }

    suspend fun getUserInfo(userId: Int): Result<Pair<String, String>> {
        return try {
            val coinInfo = wanApiService.getUserSharedArticles(userId, 1).requireData().coinInfo
            Result.Success(coinInfo.username to "ID:${coinInfo.userId} 排名:${coinInfo.rank} 积分:${coinInfo.coinCount}")
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}