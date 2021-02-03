package com.ke.wanandroid.api

import androidx.annotation.IntRange
import com.ke.wanandroid.api.response.*
import retrofit2.http.*

interface WanApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): WanBaseResponse<WanLoginResponse>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): WanBaseResponse<WanLoginResponse>

    /**
     * 退出登录
     */
    @GET("/user/logout/json")
    suspend fun logout(): WanBaseResponse<Any>

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
    suspend fun getHomeArticleList(@Path("page") @IntRange(from = 0) page: Int): WanBaseResponse<WanListResponse<WanArticleResponse>>


    /**
     * 获取公众号分类
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getOfficialAccountsTree(): WanBaseListResponse<WanTopicResponse>

    /**
     * 获取某个公众号的历史文章
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getBlogArticles(
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
     * 导航
     */
    @GET("/navi/json")
    suspend fun getNavigationTree(): WanBaseListResponse<WanNavigationResponse>

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

    /**
     * 获取积分列表
     */
    @GET("coin/rank/{page}/json")
    suspend fun getCoinRank(@Path("page") page: Int): WanBaseResponse<WanListResponse<WanUserInfoResponse>>

    /**
     * 获取用户分享的文章
     */
    @GET("/user/{userId}/share_articles/{page}/json")
    suspend fun getUserSharedArticles(
        @Path("userId") userId: Int,
        @Path("page") page: Int
    ): WanBaseResponse<WanUserArticleResponse>

    /**
     * 获取用户收藏的文章
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getUserCollectionArticles(
        @Path("page") page: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 收藏文章
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(
        @Path("id") id: Int
    ): WanBaseResponse<Any>

    /**
     * 从我的收藏里面取消收藏
     */
    @POST("/lg/uncollect/{id}/json")
    suspend fun cancelCollectArticle(
        @Path("id") id: Int,
        @Query("originId") originId: Int
    ): WanBaseResponse<Any>

    /**
     * 从文章列表页面取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): WanBaseResponse<Any>

    /**
     * 获取自己分享的文章
     */
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun getMySharedArticles(
        @Path("page") page: Int
    ): WanBaseResponse<WanUserArticleResponse>

    /**
     * 删除我分享出去的文章
     */
    @POST("/lg/user_article/delete/{id}/json")
    suspend fun deleteMyShareArticle(
        @Path("id") id: Int
    ): WanBaseResponse<Any>

    /**
     * 分享文章
     */
    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun shareArticle(
        @Field("title") title: String,
        @Field("link") link: String
    ): WanBaseResponse<Any>

    /**
     * 项目分类
     */
    @GET("/project/tree/json")
    suspend fun getProjectTopics(): WanBaseListResponse<WanTopicResponse>

    /**
     * 项目文章列表
     */
    @GET("/project/list/{page}/json")
    suspend fun getProjectArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 广场文章数据
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticles(
        @Path("page") page: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 问答
     */
    @GET("/wenda/list/{page}/json")
    suspend fun getAnswers(
        @Path("page") page: Int
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 搜索文章
     */
    @POST("/article/query/{page}/json")
    suspend fun search(
        @Path("page") @IntRange(from = 0) page: Int,
        @Query("k") keyword: String
    ): WanBaseResponse<WanListResponse<WanArticleResponse>>

    /**
     * 热搜
     */
    @GET("/hotkey/json")
    suspend fun getHotKeys(): WanBaseListResponse<WanHotKeyResponse>

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }
}