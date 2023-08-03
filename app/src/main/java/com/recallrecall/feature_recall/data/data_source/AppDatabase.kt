package com.recallrecall.feature_recall.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.recallrecall.feature_recall.domain.model.Message
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract val messageDao: MessageDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration()
        .build()
  }

  @Provides
  fun provideMessageDao(database: AppDatabase): MessageDao {
    return database.messageDao
  }
}
