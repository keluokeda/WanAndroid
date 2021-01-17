package com.ke.wanandroid.common.event

import com.ke.mvvm.base.bus.RxBus
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBus @Inject constructor() {

    fun post(event: Any) {
        RxBus.post(event)
    }


    fun <T> toObservable(tClass: Class<T>): Observable<T> {
        return RxBus.toObservable(tClass)
    }
}