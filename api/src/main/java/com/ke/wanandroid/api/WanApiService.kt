package com.ke.wanandroid.api

import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanLoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): WanBaseResponse<WanLoginResponse>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") passWord: String,
        @Field("repassword") rePassWord: String
    ): WanBaseResponse<WanLoginResponse>

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }
}