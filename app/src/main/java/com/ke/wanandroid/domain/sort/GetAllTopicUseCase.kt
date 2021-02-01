package com.ke.wanandroid.domain.sort

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.wanandroid.common.data.TopicsRepository
import com.ke.wanandroid.common.db.Topic
import javax.inject.Inject

class GetAllTopicUseCase @Inject constructor(private val topicsRepository: TopicsRepository) :
    LiveDataUseCase<Int, List<Topic>> {
    override fun invoke(parameters: Int): LiveData<List<Topic>> {
        return if (parameters == Topic.TYPE_PROJECT) {
            topicsRepository.getAllProjectTopics()
        } else {
            topicsRepository.getAllOfficialAccountsTopics()
        }
    }
}