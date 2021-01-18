package com.ke.wanandroid.common.data

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface UserDataStore {
    /**
     * 是否登录
     */
    var isLogin: Boolean

    /**
     * 用户名
     */
    var username: String?

    /**
     * 积分
     */
    var coinCount: Int

    /**
     * 等级
     */
    var level: Int

    /**
     * 排名
     */
    var rank: Int

    /**
     * 用户id
     */
    var userId: Int

    /**
     * 用户头像
     */
    var userIconImagePath: String?

    /**
     * 我的头部背景图
     */
    var meHeaderBackgroundImagePath: String?
}

@Singleton
class UserDataStoreImpl @Inject constructor(@ApplicationContext private val applicationContext: Context) :
    UserDataStore {
    private val sharedPreferences =
        applicationContext.getSharedPreferences("user-data-store", Context.MODE_PRIVATE)

    override var isLogin: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGIN, false)
        set(value) {
            sharedPreferences.edit {
                putBoolean(KEY_IS_LOGIN, value)
            }

        }

    override var username: String?
        get() = sharedPreferences.getString(KEY_USERNAME, null)
        set(value) {
            sharedPreferences.edit {
                putString(KEY_USERNAME, value)
            }
        }
    override var coinCount: Int
        get() = sharedPreferences.getInt(KEY_COIN_COUNT, 0)
        set(value) {
            sharedPreferences.edit {
                putInt(KEY_COIN_COUNT, value)
            }
        }
    override var level: Int
        get() = sharedPreferences.getInt(KEY_LEVEL, 0)
        set(value) {
            sharedPreferences.edit {
                putInt(KEY_LEVEL, value)
            }
        }

    override var userId: Int
        get() = sharedPreferences.getInt(KEY_USER_ID, 0)
        set(value) {
            sharedPreferences.edit {
                putInt(KEY_USER_ID, value)
            }
        }
    override var userIconImagePath: String?
        get() = sharedPreferences.getString(KEY_USER_ICON_IMAGE_PATH, null)
        set(value) {
            setString(KEY_USER_ICON_IMAGE_PATH, value)
        }
    override var meHeaderBackgroundImagePath: String?
        get() = sharedPreferences.getString(KEY_ME_HEADER_BACKGROUND_IMAGE_PATH, null)
        set(value) {
            setString(KEY_ME_HEADER_BACKGROUND_IMAGE_PATH, value)
        }

    private fun setString(key: String, value: String?) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    override var rank: Int
        get() = sharedPreferences.getInt(KEY_RANK, 0)
        set(value) {
            sharedPreferences.edit {
                putInt(KEY_RANK, value)
            }
        }

    companion object {
        private const val KEY_IS_LOGIN = "KEY_IS_LOGIN"
        private const val KEY_USERNAME = "KEY_USERNAME"
        private const val KEY_COIN_COUNT = "KEY_COIN_COUNT"
        private const val KEY_LEVEL = "KEY_LEVEL"
        private const val KEY_RANK = "KEY_RANK"
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_ICON_IMAGE_PATH = "KEY_USER_ICON_IMAGE_PATH"
        private const val KEY_ME_HEADER_BACKGROUND_IMAGE_PATH =
            "KEY_ME_HEADER_BACKGROUND_IMAGE_PATH"
    }

}