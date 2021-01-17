package com.ke.mvvm.base.data


abstract class BaseDataListRepository<Params,R> {

    abstract suspend fun getDataList(index:Int,params: Params): Result<List<R>>
}