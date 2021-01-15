package com.ke.mvvm.base.livedata;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 用来发送消费型事件的 {@link MutableLiveData}，在view生命周期重启加载的时候不会，不会把之前的最后一个数据法给view。
 * </p>
 * <p>
 * 注意该 {@link androidx.lifecycle.LiveData}同时只能有一个观察者，在一个生命周期内调用两次{@link #observe(LifecycleOwner, Observer)}会导致异常。
 * </p>
 *
 * @param <T>
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {



    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
//            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
            throw new RuntimeException("Multiple observers registered.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }

//    /**
//     * Used for cases where T is Void, to make calls cleaner.
//     */
//    @MainThread
//    public void call() {
//        setValue(null);
//    }
}
