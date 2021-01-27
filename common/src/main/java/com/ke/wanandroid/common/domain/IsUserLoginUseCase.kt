package com.ke.wanandroid.common.domain

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IsUserLoginUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userDataStore: UserDataStore
) : UseCase<Unit, Boolean>(dispatcher) {
    override suspend fun execute(parameters: Unit): Boolean {
        return userDataStore.isLogin
    }
}