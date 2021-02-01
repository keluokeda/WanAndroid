package com.ke.wanandroid.domain.project

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.wanandroid.common.data.TopicsRepository
import com.ke.wanandroid.common.db.Topic
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(private val topicsRepository: TopicsRepository) :
    LiveDataUseCase<Unit, List<Topic>> {
    override fun invoke(parameters: Unit): LiveData<List<Topic>> {
        return topicsRepository.getEnableProjectTopics()
    }
}