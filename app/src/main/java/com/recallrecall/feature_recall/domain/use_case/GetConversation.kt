package com.recallrecall.feature_recall.domain.use_case

import com.recallrecall.feature_recall.domain.repository.MessageRepository

class GetConversation(private val repository: MessageRepository) {

  suspend operator fun invoke() {
    // TODO: need pagination here
  }
}
