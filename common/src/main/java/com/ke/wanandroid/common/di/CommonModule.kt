package com.ke.wanandroid.common.di

import com.ke.wanandroid.common.data.ArticleRecordRepository
import com.ke.wanandroid.common.data.ArticleRecordRepositoryImpl
import com.ke.wanandroid.common.data.MyCollectionsRepository
import com.ke.wanandroid.common.data.MyCollectionsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CommonModule {


    @Binds
    abstract fun bindMyCollectionRepository(articleRepositoryImpl: MyCollectionsRepositoryImpl): MyCollectionsRepository

    @Binds
    abstract fun bindArticleRecordRepository(articleRecordRepositoryImpl: ArticleRecordRepositoryImpl): ArticleRecordRepository
}