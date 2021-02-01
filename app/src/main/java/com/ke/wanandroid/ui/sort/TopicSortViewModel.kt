package com.ke.wanandroid.ui.sort

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.domain.sort.GetAllTopicUseCase
import com.ke.wanandroid.domain.sort.ResortTopicListUseCase
import com.ke.wanandroid.domain.sort.UpdateTopicUseCase
import kotlinx.coroutines.launch

class TopicSortViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    getAllTopicUseCase: GetAllTopicUseCase,
    private val resortTopicListUseCase: ResortTopicListUseCase,
    private val updateTopicUseCase: UpdateTopicUseCase
) :
    BaseViewModel() {

    private val type =
        savedStateHandle.get<Int>(ExtraKey.TYPE) ?: throw RuntimeException("需要传入type")

    val topicList = getAllTopicUseCase(type)

    fun resortList(list: List<Topic>) {
        viewModelScope.launch {
            resortTopicListUseCase(list.mapIndexed { index, topic ->
                topic.updateIndex(index)
            })
        }
    }

    fun update(topic: Topic) {
        viewModelScope.launch {
            updateTopicUseCase(topic)
        }
    }
}