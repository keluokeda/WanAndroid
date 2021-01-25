package com.ke.wanandroid.mine.domain.record

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.ArticleRecordRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteAllArticleRecordsUseCase @Inject constructor(
    private val articleRecordRepository: ArticleRecordRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        articleRecordRepository.deleteAll()
    }
}