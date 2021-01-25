package com.ke.wanandroid.system.ui.system

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.category.BaseCategoryRepository
import javax.inject.Inject

class SystemRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseCategoryRepository {


    override suspend fun getTopicList(): List<WanTopicResponse> {
        return wanApiService.getSystemList().data!!
    }
}