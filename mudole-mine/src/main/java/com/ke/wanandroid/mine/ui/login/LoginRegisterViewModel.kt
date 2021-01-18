package com.ke.wanandroid.mine.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserLoginEvent
import kotlinx.coroutines.launch

class LoginRegisterViewModel @ViewModelInject constructor(
    private val loginRegisterRepository: LoginRegisterRepository,
    private val userDataStore: UserDataStore,
    private val eventBus: EventBus
) :
    BaseViewModel() {



    private val _loginResult = SingleLiveEvent<Boolean>()

    val loginResult: LiveData<Boolean>
        get() = _loginResult


    fun login(username: String, password: String, login: Boolean) {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            val result = if (login) loginRegisterRepository.login(
                username,
                password
            ) else loginRegisterRepository.register(username, password)

            _loadingViewVisible.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        userDataStore.isLogin = true
                        _loginResult.value = true
                        eventBus.post(UserLoginEvent())
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value =
                        SnackbarAction(message = "网络好像出了点问题", actionName = "重试") {
                            login(username, password, login)
                        }
                }
            }
        }
    }
}