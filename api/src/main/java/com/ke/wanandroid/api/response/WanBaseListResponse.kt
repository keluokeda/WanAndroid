package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class WanBaseListResponse<T : Parcelable>(
    val errorCode: Int,
    val errorMsg: String,
    val data: List<T>?
) : Parcelable{
    @IgnoredOnParcel
    val isSuccess: Boolean
        get() {
            return errorCode == WanBaseResponse.ERROR_CODE_SUCCESS
        }
}
