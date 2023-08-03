package com.recallrecall.feature_recall.domain.repository

import androidx.lifecycle.LiveData
import com.recallrecall.feature_recall.domain.model.Message

interface MessageRepository {
  fun getAll(): LiveData<List<Message?>?>

  suspend fun loadByIds(userIds: IntArray?): List<Message?>?

  suspend fun loadByDateAndName(startDate: String?, endDate: String?, name: String?): List<Message?>?

  suspend fun loadByName(name: String?): LiveData<List<Message?>?>

  suspend fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message?>?

  suspend fun loadAllName(): LiveData<List<String>?>

  suspend fun loadLatestByName(name: String?): Message

  suspend fun insertAll(vararg messages: Message?)

  suspend fun delete(vararg message: Message?)

  suspend fun update(vararg message: Message)
}
