package com.ke.wanandroid.mine.domain.sharedarticles

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.data.ArticlesRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ShareArticleUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articlesRepository: ArticlesRepository
) : UseCase<Pair<String, String>, WanBaseResponse<Any>>(dispatcher) {
    override suspend fun execute(parameters: Pair<String, String>): WanBaseResponse<Any> {
        return articlesRepository.shareArticle(parameters.first, parameters.second)
    }
}