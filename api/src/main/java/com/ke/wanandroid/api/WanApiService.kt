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

    /**
     * 获取项目分类
     */
    @GET("/project/tree/json")
    suspend fun getProjectTree(): WanBaseListResponse<WanTopicResponse>

    /**
     * 获取公众号分类
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getOfficialAccountsTree(): WanBaseListResponse<WanTopicResponse>

    /**
     * 获取某个公众号的历史文章
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getBlogArticle(
        @Path("id") id: Int,
        @Path("page") page: Int,
        @Query("k") keyword: String? = null
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取体系数据
     */
    @GET("/tree/json")
    suspend fun getSystemList(): WanBaseListResponse<WanTopicResponse>

    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getSystemArticleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 获取用户信息
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun getUserInfo(): WanBaseResponse<WanUserInfoResponse>

    /**
     * 获取用户积分
     */
    @GET("/lg/coin/getcount/json")
    suspend fun getUserCoinCount(): WanBaseResponse<Int>


    /**
     * 获取用户积分列表
     */
    @GET("/lg/coin/list/{page}/json")
    suspend fun getCoinList(@Path("page") page: Int): WanBaseResponse<WanListResponse<WanCoinResponse>>

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }
}