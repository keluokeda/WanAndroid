package com.ke.wanandroid.domain.search

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.api.response.WanHotKeyResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.data.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetHotKeyUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) :
    UseCase<Unit, WanBaseListResponse<WanHotKeyResponse>>(dispatcher) {
    override suspend fun execute(parameters: Unit): WanBaseListResponse<WanHotKeyResponse> {
        return searchRepository.getHotKeyWords()
    }
}