package com.ke.wanandroid.common.domain

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SetUserLoginStateUseCase @Inject constructor(
    private val userDataStore: UserDataStore,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Boolean, Boolean>(dispatcher) {
    override suspend fun execute(parameters: Boolean): Boolean {
        userDataStore.isLogin = parameters
        return userDataStore.isLogin
    }
}