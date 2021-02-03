package com.ke.wanandroid.domain.search

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import com.ke.wanandroid.data.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) :
    GetDataListUseCase<String, WanArticleResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: String): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse {
            searchRepository.getSearchResult(index, parameters)
        }
    }
}