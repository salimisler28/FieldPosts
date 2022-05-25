package com.salimisler.fieldposts.data.di

import com.salimisler.fieldposts.data.network.api.JsonPlaceholderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideJsonPlaceholderApi(retrofit: Retrofit) =
        retrofit.create(JsonPlaceholderApi::class.java)
}