package com.ke.wanandroid.mine.ui.mine

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.mine.model.UserInfo
import java.lang.Exception
import javax.inject.Inject

class MineRepository @Inject constructor(
    private val wanApiService: WanApiService,
    private val userDataStore: UserDataStore
) {

    /**
     * 从服务器获取用户信息
     */
    suspend fun getUserInfoFromWebService() = try {
        Result.Success(wanApiService.getUserInfo())
    } catch (e: Exception) {
        Result.Error(e)
    }


    /**
     * 从本地获取用户信息¬
     */
    fun getUserInfoFromLocal(): UserInfo {
        return if (userDataStore.isLogin) {
            UserInfo.LoginUser(
                userDataStore.coinCount,
                userDataStore.level,
                userDataStore.username!!,
                userDataStore.userId,
                userDataStore.rank,
                userDataStore.userIconImagePath,
                userDataStore.meHeaderBackgroundImagePath
            )
        } else {
            UserInfo.NoLogin
        }
    }

    /**
     * 保存用户信息到本地
     */
    fun saveUserInfoToLocal(wanUserInfoResponse: WanUserInfoResponse) {
        userDataStore.coinCount = wanUserInfoResponse.coinCount
        userDataStore.level = wanUserInfoResponse.level
        userDataStore.username = wanUserInfoResponse.username
        userDataStore.userId = wanUserInfoResponse.userId
        userDataStore.rank = wanUserInfoResponse.rank.toInt()
    }

    fun setUserIconImagePath(path: String) {
        userDataStore.userIconImagePath = path
    }

    fun setMeHeaderBackgroundImagePath(path: String) {
        userDataStore.meHeaderBackgroundImagePath = path
    }
}