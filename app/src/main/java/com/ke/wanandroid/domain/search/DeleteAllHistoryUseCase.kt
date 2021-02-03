package com.ke.wanandroid.domain.search

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.data.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteAllHistoryUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        searchRepository.deleteAllHistory()
    }
}