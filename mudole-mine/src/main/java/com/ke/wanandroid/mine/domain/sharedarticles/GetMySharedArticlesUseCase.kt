package com.ke.wanandroid.mine.domain.sharedarticles

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.ArticlesRepository
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMySharedArticlesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articlesRepository: ArticlesRepository
) : GetDataListUseCase<Unit, WanArticleResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Unit): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse {
            articlesRepository.getMySharedArticles(index)
        }
    }

}