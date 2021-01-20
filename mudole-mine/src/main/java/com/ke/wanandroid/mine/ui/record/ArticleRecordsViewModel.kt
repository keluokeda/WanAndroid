package com.ke.wanandroid.mine.ui.record

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.model.toRecord
import kotlinx.coroutines.launch

class ArticleRecordsViewModel @ViewModelInject constructor(private val articleRecordRepository: ArticleRecordsRepository) :
    BaseViewModel() {


    val articles = articleRecordRepository.allRecord.map { list ->
        list.map { it.toArticle() }
    }


    fun delete(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            articleRecordRepository.delete(wanArticleResponse.toRecord())
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            articleRecordRepository.deleteAll()
        }
    }
}