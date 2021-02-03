package com.ke.wanandroid.common.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey
    val keyword: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Date
)
