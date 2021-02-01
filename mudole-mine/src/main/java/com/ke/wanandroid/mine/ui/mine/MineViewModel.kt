package com.ke.wanandroid.mine.ui.mine

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.data.successOr
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.domain.IsUserLoginUseCase
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserLoginEvent
import com.ke.wanandroid.common.event.UserLogoutEvent
import com.ke.wanandroid.mine.domain.mine.*
import com.ke.wanandroid.mine.model.UserInfo
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch

class MineViewModel @ViewModelInject constructor(
    private val isUserLoginUseCase: IsUserLoginUseCase,
    private val getLocalUserInfoUseCase: GetLocalUserInfoUseCase,
    private val getRemoteUserInfoUseCase: GetRemoteUserInfoUseCase,
    private val saveUserInfoToLocalUseCase: SaveUserInfoToLocalUseCase,
    private val saveUserAvatarImagePathUseCase: SaveUserAvatarImagePathUseCase,
    private val saveMineHeaderImagePathUseCase: SaveMineHeaderImagePathUseCase,
    eventBus: EventBus
) :
    BaseViewModel() {
    private val _isRefreshing = MutableLiveData<Boolean>()

    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _refreshEnable = MutableLiveData<Boolean>()

    val refreshEnable: LiveData<Boolean>
        get() = _refreshEnable

    private val _userInfo = MutableLiveData<UserInfo>()

    val userInfo: LiveData<UserInfo>
        get() = _userInfo


    private val _event = SingleLiveEvent<Int>()

    val event: LiveData<Int>
        get() = _event


    init {

        refreshFromLocal()

        eventBus.toObservable(UserLoginEvent::class.java)
            .subscribe {
                refreshLoginUser()
            }.addTo(compositeDisposable)

        eventBus.toObservable(UserLogoutEvent::class.java)
            .subscribe {
                refreshFromLocal()
            }.addTo(compositeDisposable)
    }


    private fun refreshFromLocal() {
        viewModelScope.launch {
            val result = getLocalUserInfoUseCase(Unit).successOr(UserInfo.NoLogin)
            _userInfo.value = result
            _refreshEnable.value = result is UserInfo.LoginUser
        }
    }

    fun avatarClicked() {
        viewModelScope.launch {
            if (isUserLoginUseCase(Unit).successOr(false)) {
                _event.value = EVENT_PICK_AVATAR
            } else {
                _event.value = EVENT_TO_LOGIN
            }
        }

    }


    fun headerClicked() {
        viewModelScope.launch {
            if (isUserLoginUseCase(Unit).successOr(false)) {
                _event.value = EVENT_PICK_BACKGROUND
            }
        }

    }

    fun setUserIconImagePath(path: String?) {
        if (path == null) {
            return
        }
        viewModelScope.launch {
            _userInfo.value = saveUserAvatarImagePathUseCase(path).successOr(UserInfo.NoLogin)
        }

    }

    fun setHeaderImageUri(path: String?) {
        if (path == null) {
            return
        }
        viewModelScope.launch {
            _userInfo.value = saveMineHeaderImagePathUseCase(path).successOr(UserInfo.NoLogin)
        }

    }

    fun refreshLoginUser() {
        viewModelScope.launch {
            _isRefreshing.value = true
            val result = getRemoteUserInfoUseCase(Unit)
            _isRefreshing.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        //成功获取到用户数据

                        _userInfo.value =
                            saveUserInfoToLocalUseCase(result.data.requireData()).successOr(UserInfo.NoLogin)
                    }

                }
                is Result.Error -> {

                }
            }
        }
    }

    companion object {
        const val EVENT_PICK_AVATAR = 0
        const val EVENT_PICK_BACKGROUND = 1
        const val EVENT_TO_LOGIN = 2
    }
}