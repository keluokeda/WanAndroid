package com.ke.mvvm.base.data

data class ListResult<out T>(
    val list: List<T> = emptyList(),
    val over: Boolean = false,
    val errorMessage: String = "",
    val canRetry: Boolean = false
) {


    val isSuccess: Boolean
        get() = errorMessage.isEmpty()
}
