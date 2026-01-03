package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.BoardDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonModule {

    @Provides
    @Singleton
    fun provideBoardDatabase(context: Context): BoardDataBase {
        return Room.databaseBuilder(
            context,
            BoardDataBase::class.java,
            "BoardDatabase"
        )
            .build()
    }

}