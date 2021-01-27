package com.ke.wanandroid.mine.domain.mine

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import com.ke.wanandroid.mine.model.UserInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveUserInfoToLocalUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : UseCase<WanUserInfoResponse, UserInfo>(dispatcher) {
    override suspend fun execute(parameters: WanUserInfoResponse): UserInfo {
        userRepository.saveUserInfoToLocal(parameters)
        return userRepository.getCurrentUserInfoFromLocal()
    }
}