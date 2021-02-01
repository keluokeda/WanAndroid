package com.ke.wanandroid.common.data

import androidx.lifecycle.LiveData
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.common.db.TopicDao
import javax.inject.Inject

interface TopicsRepository {

    /**
     * 获取公众号主题
     */
    fun getEnableOfficialAccountsTopics(): LiveData<List<Topic>>

    suspend fun loadOfficialAccountsTopicsFromRemote()


    fun getEnableProjectTopics(): LiveData<List<Topic>>

    suspend fun loadProjectTopicsFromRemote()

    fun getAllProjectTopics(): LiveData<List<Topic>>

    fun getAllOfficialAccountsTopics(): LiveData<List<Topic>>

    suspend fun updateTopics(list: List<Topic>)

    suspend fun update(topic: Topic)
}

class TopicsRepositoryImpl @Inject constructor(
    private val topicDao: TopicDao,
    private val wanApiService: WanApiService
) : TopicsRepository {
    override fun getEnableOfficialAccountsTopics(): LiveData<List<Topic>> {
        return topicDao.getEnableOfficialAccountsTopics()
    }

    override suspend fun loadOfficialAccountsTopicsFromRemote() {
        val currentList = topicDao.getOfficialAccountsTopics()
        val resultTopicList = wanApiService.getOfficialAccountsTree().data ?: return

        val result = resultTopicList.mapIndexed { index, wanTopicResponse ->
            val topic = currentList.find {
                it.id == wanTopicResponse.id
            }
            if (topic == null) {
                return@mapIndexed Topic.fromWanTopicResponse(
                    index,
                    Topic.TYPE_OFFICIAL_ACCOUNTS,
                    wanTopicResponse
                )
            } else {

                return@mapIndexed topic.updateFromWanTopicResponse(wanTopicResponse)!!
            }

        }

        topicDao.insert(result)
    }

    override fun getEnableProjectTopics(): LiveData<List<Topic>> {
        return topicDao.getEnableProjectTopics()
    }

    override suspend fun loadProjectTopicsFromRemote() {
        val currentList = topicDao.getProjectTopics()
        val resultTopicList = wanApiService.getProjectTopics().data ?: return

        val result = resultTopicList.mapIndexed { index, wanTopicResponse ->
            val topic = currentList.find {
                it.id == wanTopicResponse.id
            }
            if (topic == null) {
                return@mapIndexed Topic.fromWanTopicResponse(
                    index,
                    Topic.TYPE_PROJECT,
                    wanTopicResponse
                )
            } else {

                return@mapIndexed topic.updateFromWanTopicResponse(wanTopicResponse)!!
            }

        }

        topicDao.insert(result)
    }

    override fun getAllProjectTopics(): LiveData<List<Topic>> {
        return topicDao.getAllProjectTopics()
    }

    override fun getAllOfficialAccountsTopics(): LiveData<List<Topic>> {
        return topicDao.getAllOfficialAccountsTopics()
    }

    override suspend fun updateTopics(list: List<Topic>) {
        topicDao.update(list)
    }

    override suspend fun update(topic: Topic) {
        topicDao.update(topic)
    }

}