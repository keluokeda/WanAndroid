package com.ke.wanandroid.mine.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanLoginResponse
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.mine.model.UserInfo
import javax.inject.Inject

interface UserRepository {

    /**
     * 根据id获取用户信息
     */
    suspend fun getUserInfoById(userId: Int): WanBaseResponse<WanUserInfoResponse>

    /**
     * 从服务器获取当前用户信息
     */
    suspend fun getCurrentUserInfoFromRemote(): WanBaseResponse<WanUserInfoResponse>


    /**
     * 获取本地保存的用户信息
     */
    fun getCurrentUserInfoFromLocal(): UserInfo

    /**
     * 保存用户信息到本地
     */
    fun saveUserInfoToLocal(wanUserInfoResponse: WanUserInfoResponse)

    /**
     * 设置用户头像
     */
    fun setUserIconImagePath(path: String)

    /**
     * 设置头部背景图
     */
    fun setMineHeaderBackgroundImagePath(path: String)

    /**
     * 登录
     */
    suspend fun login(username: String, password: String): WanBaseResponse<WanLoginResponse>

    /**
     * 登录
     */
    suspend fun register(username: String, password: String): WanBaseResponse<WanLoginResponse>
}

class UserRepositoryImpl @Inject constructor(
    private val wanApiService: WanApiService,
    private val userDataStore: UserDataStore
) :
    UserRepository {
    override suspend fun getUserInfoById(userId: Int): WanBaseResponse<WanUserInfoResponse> {
        val response = wanApiService.getUserSharedArticles(userId, 0)
        return WanBaseResponse(response.errorCode, response.errorMsg, response.data?.coinInfo)
    }

    override suspend fun getCurrentUserInfoFromRemote(): WanBaseResponse<WanUserInfoResponse> {
        return wanApiService.getUserInfo()
    }

    override fun getCurrentUserInfoFromLocal(): UserInfo {
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

    override fun saveUserInfoToLocal(wanUserInfoResponse: WanUserInfoResponse) {
        userDataStore.coinCount = wanUserInfoResponse.coinCount
        userDataStore.level = wanUserInfoResponse.level
        userDataStore.username = wanUserInfoResponse.username
        userDataStore.userId = wanUserInfoResponse.userId
        userDataStore.rank = wanUserInfoResponse.rank.toInt()
    }

    override fun setUserIconImagePath(path: String) {
        userDataStore.userIconImagePath = path
    }

    override fun setMineHeaderBackgroundImagePath(path: String) {
        userDataStore.meHeaderBackgroundImagePath = path
    }

    override suspend fun login(
        username: String,
        password: String
    ): WanBaseResponse<WanLoginResponse> {
        return wanApiService.login(username, password)
    }

    override suspend fun register(
        username: String,
        password: String
    ): WanBaseResponse<WanLoginResponse> {
        return wanApiService.register(username, password, password)
    }


}