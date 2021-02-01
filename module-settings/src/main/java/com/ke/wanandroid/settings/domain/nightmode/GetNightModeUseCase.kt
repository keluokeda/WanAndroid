package com.ke.wanandroid.settings.domain.nightmode

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.MainDispatcher
import com.ke.wanandroid.settings.model.NightMode
import com.ke.wanandroid.settings.model.currentNightMode
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNightModeUseCase @Inject constructor(@MainDispatcher dispatcher: CoroutineDispatcher) :
    UseCase<Unit, NightMode>(dispatcher) {
    override suspend fun execute(parameters: Unit): NightMode {
        return currentNightMode
    }
}