package com.ke.wanandroid.common.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TopicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Topic>)

    @Update
    fun update(list: List<Topic>)

    @Update
    fun update(topic: Topic)

    @Query("select * from topic where type = ${Topic.TYPE_PROJECT} and enable order by `index`")
    fun getEnableProjectTopics(): LiveData<List<Topic>>

    @Query("select * from topic where type = ${Topic.TYPE_PROJECT} order by `index`")
    suspend fun getProjectTopics(): List<Topic>

    @Query("select * from topic where type = ${Topic.TYPE_PROJECT} order by `index`")
    fun getAllProjectTopics(): LiveData<List<Topic>>

    @Query("select * from topic where type = ${Topic.TYPE_OFFICIAL_ACCOUNTS} and enable order by `index`")
    fun getEnableOfficialAccountsTopics(): LiveData<List<Topic>>

    @Query("select * from topic where type = ${Topic.TYPE_OFFICIAL_ACCOUNTS} order by `index`")
    fun getAllOfficialAccountsTopics(): LiveData<List<Topic>>

    @Query("select * from topic where type = ${Topic.TYPE_OFFICIAL_ACCOUNTS} order by `index`")
    suspend fun getOfficialAccountsTopics(): List<Topic>
}