package com.ke.wanandroid.mine.domain.coin

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import com.ke.wanandroid.mine.data.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCoinRankUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val coinRepository: CoinRepository
) : GetDataListUseCase<Unit, WanUserInfoResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Unit): ListResult<WanUserInfoResponse> {
        return getListResultFromWanResponse() {
            coinRepository.getCoinRank(index)
        }
    }
}