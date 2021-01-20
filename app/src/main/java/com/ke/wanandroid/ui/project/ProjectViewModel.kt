package com.ke.wanandroid.ui.project

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.tab.BaseTabViewModel


class ProjectViewModel @ViewModelInject constructor(private val projectRepository: ProjectRepository) :
    BaseTabViewModel() {
    init {
        loadData()
    }

    override suspend fun getTopics(): Result<WanBaseListResponse<WanTopicResponse>> {
        return projectRepository.getTopicList()
    }


}