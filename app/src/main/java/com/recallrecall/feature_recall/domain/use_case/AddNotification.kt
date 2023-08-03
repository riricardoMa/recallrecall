package com.recallrecall.feature_recall.domain.use_case

import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.domain.repository.MessageRepository

class AddNotification(private val repository: MessageRepository) {

    suspend operator fun invoke(message: Message) {
        repository.insertAll(message)
    }
}
