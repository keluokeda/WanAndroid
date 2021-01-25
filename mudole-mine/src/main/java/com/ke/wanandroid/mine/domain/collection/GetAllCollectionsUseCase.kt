package com.ke.wanandroid.mine.domain.collection

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.MyCollectionsRepository
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllCollectionsUseCase @Inject constructor(
    private val myCollectionsRepository: MyCollectionsRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    GetDataListUseCase<Unit, WanArticleResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Unit): ListResult<WanArticleResponse> {
        return getListResultFromWanResponse {
            myCollectionsRepository.getMyCollectionArticles(index)
        }
    }


}