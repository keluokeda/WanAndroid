package com.ke.wanandroid.mine.domain.mine

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.UserRepository
import com.ke.wanandroid.mine.model.UserInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLocalUserInfoUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) :
    UseCase<Unit, UserInfo>(dispatcher) {
    override suspend fun execute(parameters: Unit): UserInfo {
        return userRepository.getCurrentUserInfoFromLocal()
    }
}