package com.ke.wanandroid.model

import com.ke.wanandroid.api.response.WanHotKeyResponse
import com.ke.wanandroid.common.db.SearchHistory

sealed class SearchItem{

    object HotKeyHeader:SearchItem()

    data class HotKey(val wanHotKeyResponse: WanHotKeyResponse):SearchItem()

    object HistoryHeader:SearchItem()

    data class History(val history: SearchHistory):SearchItem()
}
