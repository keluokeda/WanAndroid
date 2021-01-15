package com.ke.wanandroid.officialaccount.ui.article

import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import javax.inject.Inject
import com.ke.mvvm.base.data.Result


class ArticleListRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseDataListRepository<WanArticleResponse>() {

    var id = 0
    var keyword: String? = null


    override suspend fun getDataList(index: Int): Result<List<WanArticleResponse>> {
        return try {
            Result.Success(
                wanApiService.getBlogArticle(id, index, keyword).data?.datas ?: emptyList()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}