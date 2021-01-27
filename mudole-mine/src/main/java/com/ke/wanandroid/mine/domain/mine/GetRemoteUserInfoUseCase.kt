package com.ke.wanandroid.mine.domain.mine

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRemoteUserInfoUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val userDataStore: UserDataStore
) :
    UseCase<Unit, WanBaseResponse<WanUserInfoResponse>>(dispatcher) {
    override suspend fun execute(parameters: Unit): WanBaseResponse<WanUserInfoResponse> {
        if (!userDataStore.isLogin) {
            throw RuntimeException("用户没有登录")
        }
        return userRepository.getCurrentUserInfoFromRemote()
    }
}