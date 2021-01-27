package com.ke.wanandroid.mine.domain.sharedarticles

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.data.ArticlesRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteMySharedArticleUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articlesRepository: ArticlesRepository
) : UseCase<Int, WanBaseResponse<Any>>(dispatcher) {
    override suspend fun execute(parameters: Int): WanBaseResponse<Any> {
        return articlesRepository.deleteSharedArticle(parameters)
    }
}