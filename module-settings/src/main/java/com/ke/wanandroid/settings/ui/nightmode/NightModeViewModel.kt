package com.ke.wanandroid.settings.ui.nightmode

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.successOr
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.settings.domain.nightmode.GetNightModeUseCase
import com.ke.wanandroid.settings.domain.nightmode.SetNightModeUseCase
import com.ke.wanandroid.settings.model.NightMode
import kotlinx.coroutines.launch

class NightModeViewModel @ViewModelInject constructor(
    private val getNightModeUseCase: GetNightModeUseCase,
    private val setNightModeUseCase: SetNightModeUseCase
) :
    BaseViewModel() {


    private val _nightMode = MutableLiveData<NightMode>()

    val nightMode: LiveData<NightMode>
        get() = _nightMode


    init {
        viewModelScope.launch {
            _nightMode.value = getNightModeUseCase(Unit).successOr(NightMode.FOLLOW_SYSTEM)
        }
    }

    fun setNightMode(nightMode: NightMode) {
        viewModelScope.launch {
            setNightModeUseCase(nightMode)
            _nightMode.value = nightMode
        }
    }
}