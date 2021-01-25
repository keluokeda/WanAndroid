package com.ke.wanandroid.api.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanTopicResponse(
    @SerializedName("children")
    val children: List<WanTopicResponse> = emptyList(),
    @SerializedName("courseId")
    val courseId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("order")
    val order: Int = 0,
    @SerializedName("parentChapterId")
    val parentChapterId: Int = 0,
    @SerializedName("userControlSetTop")
    val userControlSetTop: Boolean = false,
    @SerializedName("visible")
    val visible: Int = 0
) : Parcelable