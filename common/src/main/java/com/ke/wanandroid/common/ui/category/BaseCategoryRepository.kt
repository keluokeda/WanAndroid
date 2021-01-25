package com.ke.wanandroid.common.ui.category

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.response.WanTopicResponse

interface BaseCategoryRepository {

    suspend fun getCategoryList() = try {
        val list = mutableListOf<WanTopicResponse>()

        val result = getTopicList()

        result.forEach {
            list.add(it)
            list.addAll(it.children)
        }
        Result.Success(list)
    } catch (e: Exception) {
        Result.Error(e)
    }

    suspend fun getTopicList(): List<WanTopicResponse>
}