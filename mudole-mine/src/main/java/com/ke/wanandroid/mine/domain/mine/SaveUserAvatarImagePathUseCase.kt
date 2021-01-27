package com.ke.wanandroid.mine.domain.mine

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import com.ke.wanandroid.mine.model.UserInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveUserAvatarImagePathUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) :
    UseCase<String, UserInfo>(dispatcher) {
    override suspend fun execute(parameters: String): UserInfo {
        userRepository.setUserIconImagePath(parameters)
        return userRepository.getCurrentUserInfoFromLocal()
    }
}