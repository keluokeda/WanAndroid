package com.ke.wanandroid.domain.project

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.TopicsRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoadTopicsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val topicsRepository: TopicsRepository
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        topicsRepository.loadProjectTopicsFromRemote()
    }
}