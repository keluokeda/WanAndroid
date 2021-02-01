package com.ke.wanandroid.common.di

import com.ke.wanandroid.common.data.*
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

    @Binds
    abstract fun bindArticlesRepository(articlesRepositoryImpl: ArticlesRepositoryImpl): ArticlesRepository

    @Binds
    abstract fun bindLaterReadRepository(laterReadRepositoryImpl: LaterReadRepositoryImpl): LaterReadRepository

    @Binds
    abstract fun bindTopicsRepository(topicsRepository: TopicsRepositoryImpl): TopicsRepository
}