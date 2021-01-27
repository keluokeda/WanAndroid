package com.ke.wanandroid.mine.domain.laterread

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.wanandroid.common.data.LaterReadRepository
import com.ke.wanandroid.common.db.LaterRead
import javax.inject.Inject

class GetLaterReadListUseCase @Inject constructor(private val laterReadRepository: LaterReadRepository) :
    LiveDataUseCase<Unit, List<LaterRead>> {
    override fun invoke(parameters: Unit): LiveData<List<LaterRead>> {
        return laterReadRepository.getAll
    }

}