package com.ke.mvvm.base.data

import java.lang.Exception


abstract class BaseDataListRepository<P, R> {

    abstract suspend fun getListResult(index: Int, params: P): ListResult<R>


     open fun getListResultWhenError(exception: Exception): ListResult<R> {
        return ListResult(canRetry = true, errorMessage = "好像发生了一些错误")
    }
}