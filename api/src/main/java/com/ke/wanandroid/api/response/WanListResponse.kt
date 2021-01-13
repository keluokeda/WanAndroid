package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanListResponse<T : Parcelable>(
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val curPage: Int,
    val datas: List<T>
) : Parcelable
