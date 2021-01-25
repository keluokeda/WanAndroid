package com.ke.wanandroid.common

import android.os.Parcelable
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanListResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.orhanobut.logger.Logger

fun String.log() {
    Logger.d(this)
}

suspend fun <P, R : Parcelable> BaseDataListRepository<P, R>.getListResultFromWanResponse(method: suspend () -> WanBaseResponse<WanListResponse<R>>): ListResult<R> {
    return try {
        getListResultFromWanBaseResponse(method.invoke())
    } catch (e: Exception) {
        getListResultWhenError(e)
    }
}

suspend fun <P, R : Parcelable> GetDataListUseCase<P, R>.getListResultFromWanResponse(method: suspend () -> WanBaseResponse<WanListResponse<R>>): ListResult<R> {
    return getListResultFromWanBaseResponse(method.invoke())
}

private fun <R : Parcelable> getListResultFromWanBaseResponse(
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

fun ItemArticleBinding.bindArticle(item: WanArticleResponse, requestManager: RequestManager) {
    isNew.isVisible = item.fresh
    author.text =
        if (item.author.isNotEmpty()) item.author else (if (item.shareUser.isNotEmpty()) item.shareUser else "匿名")
    tag.isVisible = item.tags.isNotEmpty()
    tag.text = item.tags.firstOrNull()?.name
    if (item.envelopePic.isEmpty()) {
        image.isVisible = false
    } else {
        image.isVisible = true
        requestManager.load(item.envelopePic).into(image)
    }
    title.text = item.title
    desc.isVisible = item.desc.isNotEmpty()
    title.maxLines = if (item.desc.isNotEmpty()) 1 else 3
    desc.text = item.desc
    chapter.text = item.superChapterName + ":" + item.chapterName
    time.text = item.niceDate
}


