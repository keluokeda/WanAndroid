package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanNavigationResponse(
    val articles: List<WanArticleResponse> = emptyList(),
    val cid: Int = 0,
    val name: String = ""
) : Parcelable
