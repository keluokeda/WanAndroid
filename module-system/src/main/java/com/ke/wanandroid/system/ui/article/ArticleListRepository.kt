package com.ke.wanandroid.system.ui.article

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import java.lang.Exception
import javax.inject.Inject

class ArticleListRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<Int, WanArticleResponse>() {

    override suspend fun getDataList(index: Int, params: Int): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(wanApiService.getSystemArticleList(index, params).data!!.datas)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}