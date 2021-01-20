package com.ke.wanandroid.common.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ke.wanandroid.common.model.ArticleRecord

@Dao
interface ArticleRecordDao {

    @Query("select * from article_record order by added_date desc")
    fun getAll(): LiveData<List<ArticleRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleRecord: ArticleRecord)

    @Delete
    suspend fun delete(articleRecord: ArticleRecord)

    @Query("delete from article_record")
    suspend fun deleteAll()
}