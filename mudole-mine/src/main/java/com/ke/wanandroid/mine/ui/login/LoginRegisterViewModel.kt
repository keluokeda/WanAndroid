package com.ke.wanandroid.mine.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserLoginEvent
import com.ke.wanandroid.mine.domain.login.LoginUseCase
import com.ke.wanandroid.mine.domain.login.RegisterUseCase
import com.ke.wanandroid.mine.domain.login.SetUserLoginStateUseCase
import kotlinx.coroutines.launch

class LoginRegisterViewModel @ViewModelInject constructor(
    private val eventBus: EventBus,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val setUserLoginStateUseCase: SetUserLoginStateUseCase
) :
    BaseViewModel() {


    private val _loginResult = SingleLiveEvent<Boolean>()

    val loginResult: LiveData<Boolean>
        get() = _loginResult


    fun login(username: String, password: String, login: Boolean) {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            val result = if (login) loginUseCase(
                username to password
            ) else registerUseCase(username to password)

            _loadingViewVisible.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        setUserLoginStateUseCase(true)
                        eventBus.post(UserLoginEvent())
                        _loginResult.value = true
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    showRetrySnackBar {
                        login(username, password, login)
                    }
                }
            }
        }
    }
}