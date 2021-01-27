package com.ke.wanandroid.mine.di

import com.ke.wanandroid.mine.data.CoinRepository
import com.ke.wanandroid.mine.data.CoinRepositoryImpl
import com.ke.wanandroid.mine.data.UserRepository
import com.ke.wanandroid.mine.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MineModule {
    @Binds
    abstract fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}


