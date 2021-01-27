package com.ke.wanandroid.mine.domain.record

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.wanandroid.common.data.ArticleRecordRepository
import com.ke.wanandroid.common.db.ArticleRecord
import javax.inject.Inject

class GetAllArticleRecordsUseCase @Inject constructor(
    private val articleRecordRepository: ArticleRecordRepository
) :
    LiveDataUseCase<Unit, List<ArticleRecord>> {
    override fun invoke(parameters: Unit): LiveData<List<ArticleRecord>> {
        return articleRecordRepository.articleRecords
    }


}