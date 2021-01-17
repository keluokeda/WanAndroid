package com.ke.wanandroid.system.ui.system

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanTopicResponse
import java.lang.Exception
import javax.inject.Inject

class SystemRepository @Inject constructor(private val wanApiService: WanApiService) {

    suspend fun getSystemData(): Result<List<WanTopicResponse>> = try {
        val list = mutableListOf<WanTopicResponse>()

        val result = wanApiService.getSystemList().data!!

        result.forEach {
            list.add(it)
            list.addAll(it.children)
        }
        Result.Success(list)
    } catch (e: Exception) {
        Result.Error(e)
    }
}