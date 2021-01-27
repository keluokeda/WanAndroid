package com.ke.wanandroid.mine.ui.laterread

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseListViewModel
import com.ke.wanandroid.common.db.LaterRead
import com.ke.wanandroid.mine.domain.laterread.DeleteAllLaterReadUseCase
import com.ke.wanandroid.mine.domain.laterread.DeleteLaterReadUseCase
import com.ke.wanandroid.mine.domain.laterread.GetLaterReadListUseCase
import kotlinx.coroutines.launch

class LaterReadViewModel @ViewModelInject constructor(
    private val getLaterReadListUseCase: GetLaterReadListUseCase,
    private val deleteLaterReadUseCase: DeleteLaterReadUseCase,
    private val deleteAllLaterReadUseCase: DeleteAllLaterReadUseCase
) : BaseListViewModel<LaterRead>() {
    fun delete(laterRead: LaterRead) {
        viewModelScope.launch {
            deleteLaterReadUseCase(laterRead)
        }

    }

    fun deleteAll() {
        viewModelScope.launch {
            deleteAllLaterReadUseCase(Unit)
        }
    }

    override val dataList: LiveData<List<LaterRead>>
        get() = getLaterReadListUseCase(Unit)
}