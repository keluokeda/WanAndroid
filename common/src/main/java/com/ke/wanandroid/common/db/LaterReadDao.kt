package com.ke.wanandroid.common.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LaterReadDao {

    @Query("select * from later_read order by added_date desc")
    fun getAll(): LiveData<List<LaterRead>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(laterRead: LaterRead)

    @Delete
    suspend fun delete(laterRead: LaterRead)

    @Query("delete from later_read")
    suspend fun deleteAll()

    @Update
    suspend fun update(laterRead: LaterRead)
}