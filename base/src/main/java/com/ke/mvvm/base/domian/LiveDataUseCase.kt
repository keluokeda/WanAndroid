package com.ke.mvvm.base.domian

import androidx.lifecycle.LiveData

interface LiveDataUseCase<P, R> {

    fun execute(params: P): LiveData<R>

}