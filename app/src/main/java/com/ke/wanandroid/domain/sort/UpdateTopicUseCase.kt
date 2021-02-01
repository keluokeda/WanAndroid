package com.ke.wanandroid.domain.sort

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.TopicsRepository
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateTopicUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val topicsRepository: TopicsRepository
) : UseCase<Topic, Unit>(dispatcher) {
    override suspend fun execute(parameters: Topic) {
        topicsRepository.update(parameters)
    }
}