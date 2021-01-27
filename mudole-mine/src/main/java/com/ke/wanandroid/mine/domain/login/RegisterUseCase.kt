package com.ke.wanandroid.mine.domain.login

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.api.response.WanLoginResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : UseCase<Pair<String, String>, WanBaseResponse<WanLoginResponse>>(dispatcher) {
    override suspend fun execute(parameters: Pair<String, String>): WanBaseResponse<WanLoginResponse> {
        return userRepository.register(parameters.first, parameters.second)
    }
}