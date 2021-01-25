package com.ke.wanandroid.common.data

import androidx.lifecycle.LiveData
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.db.ArticleRecord
import com.ke.wanandroid.common.db.ArticleRecordDao
import com.ke.wanandroid.common.db.toRecord


import javax.inject.Inject
import javax.inject.Singleton

interface ArticleRecordRepository {
    val articleRecords: LiveData<List<ArticleRecord>>
    suspend fun insert(wanArticleResponse: WanArticleResponse)
    suspend fun deleteAll()
    suspend fun delete(articleRecord: ArticleRecord)
    suspend fun update(record: ArticleRecord)
}

@Singleton
class ArticleRecordRepositoryImpl @Inject constructor(private val articleRecordDao: ArticleRecordDao) :
    ArticleRecordRepository {
    override val articleRecords: LiveData<List<ArticleRecord>>
        get() = articleRecordDao.getAll()

    override suspend fun insert(wanArticleResponse: WanArticleResponse) {
        articleRecordDao.insert(wanArticleResponse.toRecord())
    }

    override suspend fun deleteAll() {
        articleRecordDao.deleteAll()
    }

    override suspend fun delete(articleRecord: ArticleRecord) {
        articleRecordDao.delete(articleRecord)
    }

    override suspend fun update(record: ArticleRecord) {
        articleRecordDao.update(record)
    }

}