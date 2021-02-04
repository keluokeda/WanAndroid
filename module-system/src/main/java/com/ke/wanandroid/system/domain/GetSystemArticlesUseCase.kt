package com.ke.wanandroid.system.domain

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import com.ke.wanandroid.system.data.SystemRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSystemArticlesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val systemRepository: SystemRepository
) :
    GetDataListUseCase<Int, WanArticleResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Int): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse() {
            systemRepository.getArticles(index, parameters)
        }
    }
}