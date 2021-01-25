package com.ke.wanandroid.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticleRecord::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class WanAndroidDataBase : RoomDatabase() {
    abstract fun articleRecordDao(): ArticleRecordDao
}