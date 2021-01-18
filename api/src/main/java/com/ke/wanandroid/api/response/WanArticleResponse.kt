package com.ke.wanandroid.api.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanArticleResponse(
//    @SerializedName("apkLink")
//    val apkLink: String?,
//    @SerializedName("audit")
//    val audit: Int?,
    @SerializedName("author")
    val author: String = "",
//    @SerializedName("canEdit")
//    val canEdit: Boolean?,
    @SerializedName("chapterId")
    val chapterId: Int = 0,
    @SerializedName("chapterName")
    val chapterName: String = "",
    @SerializedName("collect")
    val collect: Boolean = true,
    @SerializedName("courseId")
    val courseId: Int = 0,
    @SerializedName("desc")
    val desc: String = "",
//    @SerializedName("descMd")
//    val descMd: String,
    @SerializedName("envelopePic")
    val envelopePic: String = "",
    @SerializedName("fresh")
    val fresh: Boolean = false,
//    @SerializedName("host")
//    val host: String,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("niceDate")
    val niceDate: String = "",
//    @SerializedName("niceShareDate")
//    val niceShareDate: String,
//    @SerializedName("origin")
//    val origin: String,
//    @SerializedName("prefix")
//    val prefix: String,
//    @SerializedName("projectLink")
//    val projectLink: String,
//    @SerializedName("publishTime")
//    val publishTime: Long,
//    @SerializedName("realSuperChapterId")
//    val realSuperChapterId: Int,
//    @SerializedName("selfVisible")
//    val selfVisible: Int,
//    @SerializedName("shareDate")
//    val shareDate: Long,
    @SerializedName("shareUser")
    val shareUser: String = "",
    @SerializedName("superChapterId")
    val superChapterId: Int = 0,
    @SerializedName("superChapterName")
    val superChapterName: String = "",
    @SerializedName("tags")
    val tags: List<Tag> = emptyList(),
    @SerializedName("title")
    val title: String = "",
//    @SerializedName("type")
//    val type: Int,
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("visible")
    val visible: Int = 0,
    @SerializedName("zan")
    val zan: Int = 0
) : Parcelable

