package com.ke.wanandroid.api.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanUserInfoResponse(
    @SerializedName("coinCount")
    val coinCount: Int,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("username")
    val username: String
):Parcelable