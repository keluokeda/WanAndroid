package com.ke.wanandroid.mine.domain.sharedarticles

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) :
    UseCase<Int, WanBaseResponse<WanUserInfoResponse>>(dispatcher) {
    override suspend fun execute(parameters: Int): WanBaseResponse<WanUserInfoResponse> {
        return userRepository.getUserInfoById(parameters)
    }
}