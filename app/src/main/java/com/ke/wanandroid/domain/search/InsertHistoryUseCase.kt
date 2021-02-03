package com.ke.wanandroid.domain.search

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.db.SearchHistory
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.data.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.util.*
import javax.inject.Inject

class InsertHistoryUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Unit>(dispatcher) {
    override suspend fun execute(parameters: String) {
        searchRepository.insert(SearchHistory(parameters, Date()))
    }
}