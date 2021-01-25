package com.ke.wanandroid.mine.domain.collection

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.data.MyCollectionsRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CancelCollectMyArticleUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val myCollectionsRepository: MyCollectionsRepository
) :
    UseCase<Pair<Int, Int>, WanBaseResponse<Any>>(dispatcher) {
    override suspend fun execute(parameters: Pair<Int, Int>): WanBaseResponse<Any> {
        return myCollectionsRepository.cancelCollectMyArticle(parameters.first, parameters.second)
    }
}