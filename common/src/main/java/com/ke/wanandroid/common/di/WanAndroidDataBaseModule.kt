package com.ke.wanandroid.common.di

import android.content.Context
import androidx.room.Room
import com.ke.wanandroid.common.db.ArticleRecordDao
import com.ke.wanandroid.common.db.LaterReadDao
import com.ke.wanandroid.common.db.WanAndroidDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object WanAndroidDataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): WanAndroidDataBase {
        return Room.databaseBuilder(context, WanAndroidDataBase::class.java, "wan-android.db")
            .build()
    }

    @Provides
    fun provideArticleRecordDao(wanAndroidDataBase: WanAndroidDataBase): ArticleRecordDao =
        wanAndroidDataBase.articleRecordDao()

    @Provides
    fun provideLaterReadDao(wanAndroidDataBase: WanAndroidDataBase): LaterReadDao =
        wanAndroidDataBase.laterReadDao()
}