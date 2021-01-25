package com.ke.wanandroid.common.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ke.wanandroid.api.response.WanArticleResponse
import java.util.*

@Entity(tableName = "article_record")
data class ArticleRecord(
    @PrimaryKey
    val id: Int,
    val fresh: Boolean,
    val author: String,
    val link: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    var collect: Boolean,
    val title: String,
    val desc: String,
    @ColumnInfo(name = "nice_data")
    val niceData: String,
    @ColumnInfo(name = "super_chapter_name")
    val superChapterName: String,
    @ColumnInfo(name = "chapter_name")
    val chapterName: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Date
) {
    fun toArticle(): WanArticleResponse {
        return WanArticleResponse(
            id = id,
            fresh = fresh,
            author = author,
            link = link,
            envelopePic = imageUrl,
            collect = collect,
            title = title,
            desc = desc,
            niceDate = niceData,
            superChapterName = superChapterName,
            chapterName = chapterName
        )
    }
}

fun WanArticleResponse.toRecord(): ArticleRecord {
    return ArticleRecord(
        id,
        fresh,
        author,
        link,
        envelopePic,
        collect,
        title,
        desc,
        niceDate,
        superChapterName,
        chapterName,
        Date()
    )
}


