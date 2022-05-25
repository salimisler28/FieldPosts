package com.salimisler.fieldposts.data.di

import android.app.Application
import android.content.Context
import com.salimisler.fieldposts.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSqlDriver(
        @ApplicationContext context: Context
    ): AndroidSqliteDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = context,
            name = "app_db"
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        driver: AndroidSqliteDriver
    ): AppDatabase {
        return AppDatabase.invoke(driver)
    }
}