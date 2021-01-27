package com.ke.wanandroid.h5

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.domain.articlerecord.InsertArticleRecordUseCase
import kotlinx.coroutines.launch

class ArticleWebViewViewModel @ViewModelInject constructor(private val insertArticleRecordUseCase: InsertArticleRecordUseCase) :
    BaseViewModel() {

    fun insertRecord(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            insertArticleRecordUseCase(wanArticleResponse)
        }
    }
}