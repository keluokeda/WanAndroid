package com.ke.wanandroid.domain.navigation

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNavigationCategoryUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val wanApiService: WanApiService
) : UseCase<Unit, List<WanTopicResponse>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<WanTopicResponse> {
        val list = mutableListOf<WanTopicResponse>()

        val result = getTopicList()

        result.forEach {
            list.add(it)
            list.addAll(it.children)
        }
        return list
    }

    private suspend fun getTopicList(): List<WanTopicResponse> {
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