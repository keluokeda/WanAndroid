package com.ke.wanandroidapi.response

data class WanBaseResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T?
)