package com.ke.wanandroid.mine.domain.laterread

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.LaterReadRepository
import com.ke.wanandroid.common.db.LaterRead
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteLaterReadUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val laterReadRepository: LaterReadRepository
) :
    UseCase<LaterRead, Unit>(dispatcher) {
    override suspend fun execute(parameters: LaterRead) {
        laterReadRepository.delete(parameters)
    }
}