package com.ke.wanandroid.api.response

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


data class WanBaseResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T?
) {
    @IgnoredOnParcel
    val isSuccess: Boolean
        get() {
            return errorCode == ERROR_CODE_SUCCESS
        }

    fun requireData(): T = data!!

    companion object {
        const val ERROR_CODE_SUCCESS = 0
    }
}

