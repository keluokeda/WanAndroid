package com.ke.wanandroid.settings.domain.nightmode

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.MainDispatcher
import com.ke.wanandroid.settings.model.NightMode
import com.ke.wanandroid.settings.model.currentNightMode
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SetNightModeUseCase @Inject constructor(@MainDispatcher dispatcher: CoroutineDispatcher) :
    UseCase<NightMode, Unit>(dispatcher) {
    override suspend fun execute(parameters: NightMode) {
        currentNightMode = parameters
    }
}