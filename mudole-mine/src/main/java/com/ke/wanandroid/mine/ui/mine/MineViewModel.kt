package com.ke.wanandroid.mine.ui.mine

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserLoginEvent
import com.ke.wanandroid.mine.model.UserInfo
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch

class MineViewModel @ViewModelInject constructor(
    private val mineRepository: MineRepository,
    private val userDataStore: UserDataStore,
    private val eventBus: EventBus
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
                _refreshEnable.value = userDataStore.isLogin
                refresh()
            }.addTo(compositeDisposable)
    }

    private fun refreshFromLocal() {
        _userInfo.value = mineRepository.getUserInfoFromLocal()
    }

    fun avatarClicked() {
        if (userDataStore.isLogin) {
            _event.value = EVENT_PICK_AVATAR
        } else {
            _event.value = EVENT_TO_LOGIN
        }
    }

    fun nameClicked() {
        if (userDataStore.isLogin) {
            return
        }
        _event.value = EVENT_TO_LOGIN
    }

    fun headerClicked() {
        if (userDataStore.isLogin) {
            _event.value = EVENT_PICK_BACKGROUND
        }
    }

    fun setUserIconImagePath(path: String?) {
        if (path == null) {
            return
        }
        mineRepository.setUserIconImagePath(path)
        refreshFromLocal()
    }

    fun setHeaderImageUri(path: String?) {
        if (path != null) {
            mineRepository.setMeHeaderBackgroundImagePath(path)
            refreshFromLocal()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            val result = mineRepository.getUserInfoFromWebService()
            _isRefreshing.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        //成功获取到用户数据
                        mineRepository.saveUserInfoToLocal(result.data.requireData())
                        refreshFromLocal()
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