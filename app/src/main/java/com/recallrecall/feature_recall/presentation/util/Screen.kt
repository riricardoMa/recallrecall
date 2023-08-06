package com.recallrecall.feature_recall.presentation.util

sealed class Screen(val route: String) {
  object MessageScreen : Screen(route = "message_screen")

  object ConversationScreen: Screen(route = "conversation_screen")
}
