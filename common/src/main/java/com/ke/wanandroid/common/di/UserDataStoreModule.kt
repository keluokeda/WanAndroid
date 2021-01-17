package com.ke.wanandroid.common.di

import com.ke.wanandroid.common.data.UserDataStore
import com.ke.wanandroid.common.data.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UserDataStoreModule {

    @Binds
    abstract fun bindUserDataStore(userDataStoreImpl: UserDataStoreImpl): UserDataStore
}