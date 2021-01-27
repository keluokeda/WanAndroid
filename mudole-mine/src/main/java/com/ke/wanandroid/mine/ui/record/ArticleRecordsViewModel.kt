package com.ke.wanandroid.mine.ui.record

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseListViewModel
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.db.ArticleRecord
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.mine.domain.record.DeleteAllArticleRecordsUseCase
import com.ke.wanandroid.mine.domain.record.DeleteArticleRecordUseCase
import com.ke.wanandroid.mine.domain.record.GetAllArticleRecordsUseCase
import com.ke.wanandroid.mine.domain.record.UpdateRecordUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class ArticleRecordsViewModel @ViewModelInject constructor(
    private val getAllArticleRecordsUseCase: GetAllArticleRecordsUseCase,
    private val deleteAllArticleRecordsUseCase: DeleteAllArticleRecordsUseCase,
    private val deleteArticleRecordUseCase: DeleteArticleRecordUseCase,
    private val collectArticleUseCase: CollectArticleUseCase,
    private val cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    private val updateRecordUseCase: UpdateRecordUseCase,
    @ApplicationContext private val context: Context
) :
    BaseListViewModel<ArticleRecord>() {
    override val dataList: LiveData<List<ArticleRecord>>
        get() = getAllArticleRecordsUseCase(Unit)

    fun deleteAll() {
        viewModelScope.launch {
            deleteAllArticleRecordsUseCase.invoke(Unit)
        }
    }

    fun delete(articleRecord: ArticleRecord) {
        viewModelScope.launch {
            deleteArticleRecordUseCase(articleRecord)
        }
    }

    fun collectArticle(articleRecord: ArticleRecord) {
        if (articleRecord.collect) {
            return
        }

        viewModelScope.launch {
            when (val result = collectArticleUseCase.invoke(articleRecord.id)) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        _snackbarEvent.value = SnackbarAction(
                            message = "收藏成功"
                        )
                        articleRecord.collect = true
                        updateRecordUseCase.invoke(articleRecord)
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value =
                        SnackbarAction(
                            message = context.getString(R.string.action_retry_hint),
                            actionName = "重试"
                        ) {
                            collectArticle(articleRecord)
                        }
                }
            }

        }
    }

    fun cancelCollectArticle(articleRecord: ArticleRecord) {
        if (!articleRecord.collect) {
            return
        }
        viewModelScope.launch {
            when (val result = cancelCollectArticleUseCase.invoke(articleRecord.id)) {
                is Result.Success -> {
                    if (result.data.isSuccess) {

                        articleRecord.collect = false
                        updateRecordUseCase.invoke(articleRecord)
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value =
                        SnackbarAction(
                            message = context.getString(R.string.action_retry_hint),
                            actionName = "重试"
                        ) {
                            cancelCollectArticle(articleRecord)
                        }
                }
            }
        }
    }
}