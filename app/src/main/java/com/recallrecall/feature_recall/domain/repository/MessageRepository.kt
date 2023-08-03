package com.recallrecall.feature_recall.domain.repository

import androidx.lifecycle.LiveData
import com.recallrecall.feature_recall.domain.model.Message

interface MessageRepository {
  fun getAll(): LiveData<List<Message?>?>

  fun loadByIds(userIds: IntArray?): List<Message?>?

  fun loadByDateAndName(startDate: String?, endDate: String?, name: String?): List<Message?>?

  fun loadByName(name: String?): LiveData<List<Message?>?>

  fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message?>?

  fun loadAllName(): LiveData<List<String>?>

  fun loadLatestByName(name: String?): Message

  fun insertAll(vararg messages: Message?)

  fun delete(vararg message: Message?)

  fun update(vararg message: Message)
}