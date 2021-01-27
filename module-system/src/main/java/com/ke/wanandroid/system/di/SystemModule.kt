package com.ke.wanandroid.system.di

import com.ke.wanandroid.system.data.SystemRepository
import com.ke.wanandroid.system.data.SystemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class SystemModule {
    @Binds
    abstract fun bindSystemRepository(systemRepositoryImpl: SystemRepositoryImpl): SystemRepository
}