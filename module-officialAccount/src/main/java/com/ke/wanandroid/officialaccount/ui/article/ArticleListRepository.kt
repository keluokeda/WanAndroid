package com.ke.wanandroid.officialaccount.ui.article

import com.ke.mvvm.base.data.ListResult
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.getListResultWithTry
import com.ke.wanandroid.common.model.BaseArticleListRepository
import javax.inject.Inject


class ArticleListRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseArticleListRepository<Pair<Int, String?>>(wanApiService) {


    override suspend fun getListResult(
        index: Int,
        params: Pair<Int, String?>
    ): ListResult<WanArticleResponse> {

        return getListResultWithTry {
            wanApiService.getBlogArticles(params.first, index, params.second)
        }
    }
}