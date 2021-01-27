package com.ke.wanandroid.common.domain.laterread

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.LaterReadRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddToLaterReadListUseCase @Inject constructor(
    @IoDispatcher
    dispatcher: CoroutineDispatcher,
    private val laterReadRepository: LaterReadRepository
) : UseCase<WanArticleResponse, Unit>(dispatcher) {
    override suspend fun execute(parameters: WanArticleResponse) {
        laterReadRepository.insert(parameters)
    }
}