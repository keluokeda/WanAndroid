package com.ke.wanandroid.mine.domain.record

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.ArticleRecordRepository
import com.ke.wanandroid.common.db.ArticleRecord
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteArticleRecordUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val articleRecordRepository: ArticleRecordRepository
) : UseCase<ArticleRecord, Unit>(dispatcher) {
    override suspend fun execute(parameters: ArticleRecord) {
        articleRecordRepository.delete(parameters)
    }

}