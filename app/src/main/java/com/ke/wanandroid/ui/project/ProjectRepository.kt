package com.ke.wanandroid.ui.project

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import javax.inject.Inject


class ProjectRepository @Inject constructor(private val wanApiService: WanApiService) {

    suspend fun getTopicList() = try {
        Result.Success(wanApiService.getProjectTopics())
    } catch (e: Exception) {
        Result.Error(e)
    }
}