package com.ke.wanandroid.common.di

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.common.log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object WanApiServiceModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    message.log()
                }

            }).apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWanApiService(okHttpClient: OkHttpClient): WanApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WanApiService.BASE_URL)
            .build().create(WanApiService::class.java)
    }
}