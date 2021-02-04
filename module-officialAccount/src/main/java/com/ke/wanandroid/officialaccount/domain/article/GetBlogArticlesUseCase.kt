package com.ke.wanandroid.officialaccount.domain.article

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.ArticlesRepository
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBlogArticlesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articlesRepository: ArticlesRepository
) : GetDataListUseCase<Pair<Int, String?>, WanArticleResponse>(dispatcher) {
    override suspend fun execute(
        index: Int,
        parameters: Pair<Int, String?>
    ): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse() {
            articlesRepository.getBlogArticles(index, parameters.first, parameters.second)
        }
    }
}