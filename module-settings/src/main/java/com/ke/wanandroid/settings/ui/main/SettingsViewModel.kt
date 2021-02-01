package com.ke.wanandroid.settings.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.data.successOr
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.domain.IsUserLoginUseCase
import com.ke.wanandroid.common.domain.SetUserLoginStateUseCase
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserLogoutEvent
import com.ke.wanandroid.settings.domain.settings.LogoutUseCase
import kotlinx.coroutines.launch

class SettingsViewModel @ViewModelInject constructor(
    private val isUserLoginUseCase: IsUserLoginUseCase,
    private val setUserLoginStateUseCase: SetUserLoginStateUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val eventBus: EventBus
) : BaseViewModel() {



    private val _logoutButtonVisible = MutableLiveData<Boolean>()

    val logoutButtonVisible: LiveData<Boolean>
        get() = _logoutButtonVisible

    private val _loadingDialogShowing = MutableLiveData<Boolean>()

    val loadingDialogShowing: LiveData<Boolean>
        get() = _loadingDialogShowing

    init {
        viewModelScope.launch {
            _logoutButtonVisible.value = isUserLoginUseCase(Unit).successOr(false)
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loadingDialogShowing.value = true
            val result = logoutUseCase(Unit)
            _loadingDialogShowing.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        setUserLoginStateUseCase(false)
                        _logoutButtonVisible.value = false
                        eventBus.post(UserLogoutEvent())
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    showRetrySnackBar {
                        logout()
                    }
                }
            }
        }
    }
}