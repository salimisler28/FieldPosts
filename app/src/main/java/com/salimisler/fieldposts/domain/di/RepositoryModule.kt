package com.salimisler.fieldposts.domain.di

import com.salimisler.fieldposts.data.repositories.JsonPlaceholderRepositoryImpl
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindJsonPlaceholderRepository(
        jsonPlaceholderRepository: JsonPlaceholderRepositoryImpl
    ): JsonPlaceholderRepository
}