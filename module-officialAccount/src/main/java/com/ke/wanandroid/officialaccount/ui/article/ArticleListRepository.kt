package com.ke.wanandroid.officialaccount.ui.article

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import javax.inject.Inject
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.common.model.BaseArticleListRepository


class ArticleListRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Pair<Int, String?>>(wanApiService) {


    override suspend fun getDataList(
        index: Int,
        params: Pair<Int, String?>
    ): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(
                wanApiService.getBlogArticle(params.first, index, params.second).data?.datas
                    ?: emptyList()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}