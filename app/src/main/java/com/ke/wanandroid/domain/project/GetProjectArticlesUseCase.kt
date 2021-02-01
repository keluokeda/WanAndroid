package com.ke.wanandroid.domain.project

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.ArticlesRepository
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetProjectArticlesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articlesRepository: ArticlesRepository
) : GetDataListUseCase<Int, WanArticleResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Int): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse {
            articlesRepository.getProjectArticles(index, parameters)
        }
    }
}