package com.ke.mvvm.base.domian

import androidx.lifecycle.LiveData

interface LiveDataUseCase<P, R> {

    operator fun invoke(parameters: P): LiveData<R>

}