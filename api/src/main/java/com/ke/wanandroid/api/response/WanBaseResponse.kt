package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanBaseResponse<T : Parcelable>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T?
):Parcelable

