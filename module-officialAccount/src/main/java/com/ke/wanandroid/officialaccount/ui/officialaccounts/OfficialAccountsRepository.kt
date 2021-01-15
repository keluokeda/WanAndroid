package com.ke.wanandroid.officialaccount.ui.officialaccounts

import com.ke.wanandroid.api.WanApiService
import java.lang.Exception
import javax.inject.Inject
import com.ke.mvvm.base.data.Result


class OfficialAccountsRepository @Inject constructor(private val wanApiService: WanApiService) {

    suspend fun getTopicList() = try {
        Result.Success(wanApiService.getOfficialAccountsTree())
    } catch (e: Exception) {
        Result.Error(e)
    }
}