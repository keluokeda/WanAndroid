package com.ke.wanandroid.settings.domain.settings

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val wanApiService: WanApiService
) : UseCase<Unit, WanBaseResponse<Any>>(dispatcher) {
    override suspend fun execute(parameters: Unit) = wanApiService.logout()

}