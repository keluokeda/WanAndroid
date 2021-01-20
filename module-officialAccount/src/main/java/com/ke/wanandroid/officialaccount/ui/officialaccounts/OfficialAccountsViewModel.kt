package com.ke.wanandroid.officialaccount.ui.officialaccounts

import androidx.hilt.lifecycle.ViewModelInject
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.tab.BaseTabViewModel


class OfficialAccountsViewModel @ViewModelInject constructor(private val officialAccountsRepository: OfficialAccountsRepository) :
    BaseTabViewModel() {


    init {
        loadData()
    }

    override suspend fun getTopics(): Result<WanBaseListResponse<WanTopicResponse>> {
        return officialAccountsRepository.getTopicList()
    }


}