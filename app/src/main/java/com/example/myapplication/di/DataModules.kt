package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.MyMovieDatabase
import com.example.myapplication.data.local.MyMovieDao
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MyMovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyMovieDatabase::class.java,
            "Movies.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MyMovieDatabase): MyMovieDao = database.myMovieDao()
}
