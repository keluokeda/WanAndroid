package com.ke.wanandroid.mine.ui.record

import com.ke.wanandroid.common.db.ArticleRecordDao
import com.ke.wanandroid.common.model.ArticleRecord
import javax.inject.Inject

class ArticleRecordsRepository @Inject constructor(private val articleRecordDao: ArticleRecordDao) {

    val allRecord = articleRecordDao.getAll()

    suspend fun delete(record: ArticleRecord) = articleRecordDao.delete(record)

    suspend fun deleteAll() = articleRecordDao.deleteAll()
}