package com.ke.wanandroid.mine.domain.laterread

import com.ke.mvvm.base.domian.UseCase
import com.ke.wanandroid.common.data.LaterReadRepository
import com.ke.wanandroid.common.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteAllLaterReadUseCase @Inject constructor(
    private val laterReadRepository: LaterReadRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        laterReadRepository.deleteAll()
    }
}