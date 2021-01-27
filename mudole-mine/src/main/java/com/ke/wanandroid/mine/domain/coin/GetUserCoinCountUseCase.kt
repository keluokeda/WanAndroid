package com.ke.wanandroid.mine.domain.coin

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.mine.data.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserCoinCountUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val coinRepository: CoinRepository
) :
    UseCase<Unit, Int>(dispatcher) {
    override suspend fun execute(parameters: Unit): Int {
        return coinRepository.getUserCoinCount().requireData()
    }
}