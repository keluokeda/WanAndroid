package com.ke.wanandroid.common.domain.articlerecord

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.data.ArticleRecordRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertArticleRecordUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val articleRecordRepository: ArticleRecordRepository
) : UseCase<WanArticleResponse, Unit>(dispatcher) {
    override suspend fun execute(parameters: WanArticleResponse) {
        articleRecordRepository.insert(parameters)
    }
}