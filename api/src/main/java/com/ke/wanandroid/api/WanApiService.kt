package com.ke.wanandroid.api

import com.ke.wanandroid.api.response.*
import retrofit2.http.*

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

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") passWord: String,
        @Field("repassword") rePassWord: String
    ): WanBaseResponse<WanLoginResponse>

    /**
     * 首页banner
     */
    @GET("banner/json")
    suspend fun getBanner(): WanBaseListResponse<WanBannerResponse>

    /**
     * 首页文章列表
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }
}