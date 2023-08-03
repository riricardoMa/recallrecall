package com.recallrecall.feature_recall.domain.use_case

data class WeChatUseCases(
    val addNotification: AddNotification,
    val updateToRecalled: UpdateToRecalled,
    val getMessages: GetMessages,
    val getConversation: GetConversation,
)