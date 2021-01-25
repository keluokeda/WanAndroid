package com.ke.wanandroid.ui.navigation

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.category.BaseCategoryRepository
import javax.inject.Inject

class NavigationRepository @Inject constructor(private val wanApiService: WanApiService) :
    BaseCategoryRepository {
    override suspend fun getTopicList(): List<WanTopicResponse> {
//        return wanApiService.getSystemList().data!!
        val list = wanApiService.getNavigationTree().data!!
        val result = mutableListOf<WanTopicResponse>()

        list.forEach { navigation ->
            val children = navigation.articles.map { WanTopicResponse(name = it.title) }
            val topic = WanTopicResponse(children, name = navigation.name)
            result.add(topic)
            result.addAll(topic.children)
        }
        return result
    }
}