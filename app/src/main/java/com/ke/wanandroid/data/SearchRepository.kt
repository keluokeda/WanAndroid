package com.ke.wanandroid.data

import androidx.lifecycle.LiveData
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.*
import com.ke.wanandroid.common.db.SearchHistory
import com.ke.wanandroid.common.db.SearchHistoryDao
import javax.inject.Inject
import javax.inject.Singleton

interface SearchRepository {

    /**
     * 搜索历史记录
     */
    val historyList: LiveData<List<SearchHistory>>

    /**
     * 插入一条历史记录
     */
    suspend fun insert(searchHistory: SearchHistory)

    /**
     * 删除一条历史记录
     */
    suspend fun delete(searchHistory: SearchHistory)

    /**
     * 删除所有的历史记录
     */
    suspend fun deleteAllHistory()

    /**
     * 获取搜索热词
     */
    suspend fun getHotKeyWords(): WanBaseListResponse<WanHotKeyResponse>

    /**
     * 获取搜索结果
     */
    suspend fun getSearchResult(
        index: Int,
        keyword: String
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>
}

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val wanApiService: WanApiService,
    private val searchHistoryDao: SearchHistoryDao
) : SearchRepository {
    override val historyList: LiveData<List<SearchHistory>>
        get() = searchHistoryDao.getAll()

    override suspend fun insert(searchHistory: SearchHistory) {
        searchHistoryDao.insert(searchHistory)
    }

    override suspend fun delete(searchHistory: SearchHistory) {
        searchHistoryDao.delete(searchHistory)
    }

    override suspend fun deleteAllHistory() {
        searchHistoryDao.deleteAll()
    }

    override suspend fun getHotKeyWords(): WanBaseListResponse<WanHotKeyResponse> {
        return wanApiService.getHotKeys()
    }

    override suspend fun getSearchResult(
        index: Int,
        keyword: String
    ): WanBaseResponse<WanListResponse<WanArticleResponse>> {
        return wanApiService.search(index, keyword)
    }

}