package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanUserArticleResponse(
    val coinInfo: WanUserInfoResponse,
    val shareArticles: WanListResponse<WanArticleResponse>
) : Parcelable