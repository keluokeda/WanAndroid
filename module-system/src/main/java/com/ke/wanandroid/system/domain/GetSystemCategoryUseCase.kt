package com.ke.wanandroid.system.domain

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.system.data.SystemRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSystemCategoryUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val systemRepository: SystemRepository
) : UseCase<Unit, List<WanTopicResponse>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<WanTopicResponse> {
        val list = mutableListOf<WanTopicResponse>()

        val result = systemRepository.getSystemList().data!!

        result.forEach {
            list.add(it)
            list.addAll(it.children)
        }
        return list
    }
}