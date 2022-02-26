package com.mehmetalivargun.countries.di

import android.content.Context
import androidx.room.Room
import com.mehmetalivargun.countries.db.CountryDAO
import com.mehmetalivargun.countries.db.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDao(
        db:CountryDatabase
    )=db.countryDAO()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,CountryDatabase::class.java,"countries"
    ).build()
}