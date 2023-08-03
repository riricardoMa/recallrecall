package com.recallrecall.di

import android.content.Context
import androidx.room.Room
import com.recallrecall.feature_recall.data.data_source.AppDatabase
import com.recallrecall.feature_recall.data.data_source.MessageDao
import com.recallrecall.feature_recall.domain.repository.MessageRepository
import com.recallrecall.feature_recall.domain.use_case.AddNotification
import com.recallrecall.feature_recall.domain.use_case.GetConversation
import com.recallrecall.feature_recall.domain.use_case.GetMessages
import com.recallrecall.feature_recall.domain.use_case.UpdateToRecalled
import com.recallrecall.feature_recall.domain.use_case.WeChatUseCases
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
            context = appContext,
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME,
        )
        .fallbackToDestructiveMigration()
        .build()
  }

  @Provides
  @Singleton
  fun provideMessageDao(database: AppDatabase): MessageDao {
    return database.messageDao
  }

    @Provides
    @Singleton
    fun provideWeChatUseCases(repository: MessageRepository): WeChatUseCases {
        return WeChatUseCases(
            addNotification = AddNotification(repository),
            updateToRecalled = UpdateToRecalled(),
            getMessages = GetMessages(),
            getConversation = GetConversation()
        )
    }

}