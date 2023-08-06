package com.recallrecall.feature_recall.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.recallrecall.feature_recall.data.data_source.MessageDao
import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(private val messageDao: MessageDao) : MessageRepository {
  override fun getAll(): LiveData<List<Message>?> = messageDao.getAll()

  override suspend fun loadByIds(userIds: IntArray?): List<Message>? = messageDao.loadByIds(userIds)

  override suspend fun loadByDateAndName(
      startDate: String?,
      endDate: String?,
      name: String?,
  ): List<Message>? = messageDao.loadByDateAndName(startDate, endDate, name)

  override suspend fun loadByName(name: String?): LiveData<List<Message>?> =
      messageDao.loadByName(name)

  override suspend fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message>? =
      messageDao.loadByNameAndRecalled(name, recalled)

  override fun loadAllName(): Flow<PagingData<String>> {
    return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { messageDao.loadAllName() })
        .flow
  }

  override fun loadLatestByName(name: String?): Flow<Message> = messageDao.loadLatestByName(name)

  override suspend fun insertAll(vararg messages: Message?) = messageDao.insertAll(*messages)

  override suspend fun delete(vararg message: Message?) = messageDao.delete(*message)

  override suspend fun update(vararg message: Message) = messageDao.update(*message)
}
