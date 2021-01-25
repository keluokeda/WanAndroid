package com.ke.wanandroid.common.domain

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.data.MyCollectionsRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CancelCollectArticleUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val myCollectionsRepository: MyCollectionsRepository
) : UseCase<Int, WanBaseResponse<Any>>(dispatcher) {
    override suspend fun execute(parameters: Int): WanBaseResponse<Any> {
        return myCollectionsRepository.cancelCollectArticle(parameters)
    }
}