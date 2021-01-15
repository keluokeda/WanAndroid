package com.ke.mvvm.base.data


abstract class BaseDataListRepository<T> {

    abstract suspend fun getDataList(index:Int): Result<List<T>>
}