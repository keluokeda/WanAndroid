package com.ke.wanandroid.common.db

import androidx.room.Entity
import com.ke.wanandroid.api.response.WanTopicResponse

@Entity(primaryKeys = ["id", "type"], tableName = "topic")
data class Topic(
    val id: Int,
    val type: Int,
    val index: Int,
    val enable: Boolean,
    val name: String
) {
    /**
     * 更新topic,注意只能更新名称
     */
    fun updateFromWanTopicResponse(wanTopicResponse: WanTopicResponse): Topic? {
        if (id != wanTopicResponse.id) {
            return null
        }

        return Topic(
            id, type, index, enable, wanTopicResponse.name
        )
    }

    fun updateIndex(newIndex: Int): Topic {
        return Topic(
            id, type, newIndex, enable, name
        )
    }

    fun updateEnable(newEnable: Boolean): Topic {
        return Topic(
            id, type, index, newEnable, name
        )
    }

    companion object {
        const val TYPE_PROJECT = 0
        const val TYPE_OFFICIAL_ACCOUNTS = 1

        fun fromWanTopicResponse(index: Int, type: Int, wanTopicResponse: WanTopicResponse): Topic {
            return Topic(
                wanTopicResponse.id,
                type,
                index,
                enable = true,
                name = wanTopicResponse.name
            )
        }
    }
}
