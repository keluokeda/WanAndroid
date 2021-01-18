package com.ke.wanandroid.mine.ui.sharedartiles

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import javax.inject.Inject

class UserSharedArticlesRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Int, WanArticleResponse>() {
    override suspend fun getDataList(index: Int, params: Int): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(
                wanApiService.getUserSharedArticles(params, index).requireData().shareArticles.datas
            )
        } catch (e: Exception) {
            Result.Error(e)
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