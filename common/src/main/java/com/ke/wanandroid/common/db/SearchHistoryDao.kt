package com.ke.wanandroid.common.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SearchHistoryDao {

    @Query("select * from search_history order by added_date desc")
    fun getAll(): LiveData<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistory: SearchHistory)

    @Query("delete from search_history")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(searchHistory: SearchHistory)
}