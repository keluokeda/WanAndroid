package com.ke.wanandroid.api.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanCoinResponse(
    @SerializedName("coinCount")
    val coinCount: Int,
    @SerializedName("date")
    val date: Long,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("userName")
    val userName: String
):Parcelable