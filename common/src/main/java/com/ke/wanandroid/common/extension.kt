package com.ke.wanandroid.common

import android.os.Parcelable
import androidx.appcompat.app.AppCompatDelegate
import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import com.orhanobut.logger.Logger

fun String.log() {
    Logger.d(this)
}

suspend fun <P, R : Parcelable> BaseDataListRepository<P, R>.getListResultWithTry(method: suspend () -> WanBaseResponse<WanListResponse<R>>): ListResult<R> {
    return try {
        getListResultFromWanBaseResponse(method.invoke())
    } catch (e: Exception) {
        getListResultWhenError(e)
    }
}


fun <R : Parcelable> getListResultFromWanBaseResponse(
    response: WanBaseResponse<WanListResponse<R>>
): ListResult<R> {
    return if (response.isSuccess) {
        ListResult(list = response.requireData().datas, over = response.requireData().over)
    } else {
        ListResult(errorMessage = response.errorMsg, canRetry = false)
    }
}

val nightModeList = listOf(
    AppCompatDelegate.MODE_NIGHT_UNSPECIFIED,
    AppCompatDelegate.MODE_NIGHT_YES,
    AppCompatDelegate.MODE_NIGHT_NO,
    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY,
    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
)
val nightModeDescriptionList = listOf(
    "默认", "深色", "浅色", "跟随电量", "跟随系统"
)

