package com.recallrecall.feature_recall.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recallrecall.feature_recall.domain.model.Message

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract val messageDao: MessageDao

  companion object {
    const val DATABASE_NAME = "app_db"
  }
}
