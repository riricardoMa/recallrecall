package com.recallrecall.di

import android.content.Context
import androidx.room.Room
import com.recallrecall.feature_recall.data.data_source.AppDatabase
import com.recallrecall.feature_recall.data.data_source.MessageDao
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
  fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME,
        )
        .fallbackToDestructiveMigration()
        .build()
  }

  @Provides
  fun provideMessageDao(database: AppDatabase): MessageDao {
    return database.messageDao
  }
}
