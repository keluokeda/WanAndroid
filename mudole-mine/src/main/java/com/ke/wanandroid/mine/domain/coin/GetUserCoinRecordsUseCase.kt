package com.ke.wanandroid.mine.domain.coin

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.wanandroid.api.response.WanCoinResponse
import com.ke.wanandroid.common.di.IoDispatcher
import com.ke.wanandroid.common.getListResultFromWanResponse
import com.ke.wanandroid.mine.data.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserCoinRecordsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val coinRepository: CoinRepository
) : GetDataListUseCase<Unit, WanCoinResponse>(dispatcher) {
    override suspend fun execute(index: Int, parameters: Unit): ListResult<WanCoinResponse> {
        return getListResultFromWanResponse {
            coinRepository.getCoinRecords(index)
        }
    }
}