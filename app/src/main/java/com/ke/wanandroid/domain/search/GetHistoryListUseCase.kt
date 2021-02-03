package com.ke.wanandroid.domain.search

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.wanandroid.common.db.SearchHistory
import com.ke.wanandroid.data.SearchRepository
import javax.inject.Inject

class GetHistoryListUseCase @Inject constructor(private val searchRepository: SearchRepository) :
    LiveDataUseCase<Unit, List<SearchHistory>> {
    override fun invoke(parameters: Unit): LiveData<List<SearchHistory>> {
        return searchRepository.historyList
    }
}