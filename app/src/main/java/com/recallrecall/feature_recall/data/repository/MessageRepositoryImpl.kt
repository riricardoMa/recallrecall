package com.recallrecall.feature_recall.data.repository

import androidx.lifecycle.LiveData
import com.recallrecall.feature_recall.data.data_source.MessageDao
import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.domain.repository.MessageRepository

class MessageRepositoryImpl(private val messageDao: MessageDao) :
    MessageRepository {
    override fun getAll(): LiveData<List<Message?>?> = messageDao.getAll()

    override fun loadByIds(userIds: IntArray?): List<Message?>? = messageDao.loadByIds(userIds)

    override fun loadByDateAndName(
        startDate: String?,
        endDate: String?,
        name: String?,
    ): List<Message?>? = messageDao.loadByDateAndName(startDate, endDate, name)

    override fun loadByName(name: String?): LiveData<List<Message?>?> = messageDao.loadByName(name)

    override fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message?>? =
        messageDao.loadByNameAndRecalled(name, recalled)

    override fun loadAllName(): LiveData<List<String>?> = messageDao.loadAllName()

    override fun loadLatestByName(name: String?): Message = messageDao.loadLatestByName(name)

    override fun insertAll(vararg messages: Message?) = messageDao.insertAll(*messages)

    override fun delete(vararg message: Message?) = messageDao.delete(*message)

    override fun update(vararg message: Message) = messageDao.update(*message)
}
