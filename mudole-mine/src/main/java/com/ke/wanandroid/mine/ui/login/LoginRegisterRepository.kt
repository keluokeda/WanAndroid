package com.ke.wanandroid.mine.ui.login

import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanLoginResponse
import java.lang.Exception
import javax.inject.Inject

class LoginRegisterRepository @Inject constructor(
    private val wanApiService: WanApiService
) {

    /**
     * 登录
     */
    suspend fun login(
        username: String,
        password: String
    ): Result<WanBaseResponse<WanLoginResponse>> {
        return try {
            val result = Result.Success(wanApiService.login(username, password))

            return result
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * 注册
     */
    suspend fun register(
        username: String,
        password: String
    ): Result<WanBaseResponse<WanLoginResponse>> {
        return try {
            Result.Success(wanApiService.register(username, password, password))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }




}