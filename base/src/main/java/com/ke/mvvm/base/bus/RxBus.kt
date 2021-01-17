package com.ke.mvvm.base.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val bus = PublishSubject.create<Any>().toSerialized()


    fun post(event: Any) {
        bus.onNext(event)
    }


    fun <T> toObservable(tClass: Class<T>): Observable<T> {
        return bus.ofType(tClass)
    }
}