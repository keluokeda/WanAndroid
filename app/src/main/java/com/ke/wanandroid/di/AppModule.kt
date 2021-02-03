package com.ke.wanandroid.di

import com.ke.wanandroid.data.NavigationRepository
import com.ke.wanandroid.data.NavigationRepositoryImpl
import com.ke.wanandroid.data.SearchRepository
import com.ke.wanandroid.data.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindNavigationRepository(navigationRepositoryImpl: NavigationRepositoryImpl): NavigationRepository
}