package com.recallrecall.repository

import androidx.lifecycle.LiveData
import com.recallrecall.db.Message
import com.recallrecall.db.MessageDao
import javax.inject.Inject
import javax.inject.Singleton

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

@Singleton
class RoomMessageRepository @Inject constructor(private val messageDao: MessageDao) : MessageRepository {
  override fun getAll(): LiveData<List<Message?>?> = messageDao.getAll()
  override fun loadByIds(userIds: IntArray?): List<Message?>? = messageDao.loadByIds(userIds)
  override fun loadByDateAndName(startDate: String?, endDate: String?, name: String?): List<Message?>? = messageDao.loadByDateAndName(startDate, endDate, name)
  override fun loadByName(name: String?): LiveData<List<Message?>?> = messageDao.loadByName(name)
  override fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message?>? = messageDao.loadByNameAndRecalled(name, recalled)
  override fun loadAllName(): LiveData<List<String>?> = messageDao.loadAllName()
  override fun loadLatestByName(name: String?): Message = messageDao.loadLatestByName(name)
  override fun insertAll(vararg messages: Message?) = messageDao.insertAll(*messages)
  override fun delete(vararg message: Message?) = messageDao.delete(*message)
  override fun update(vararg message: Message) = messageDao.update(*message)
}

