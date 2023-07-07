package com.recallrecall.ui.contactorscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ContactorScreenRoute(
  coordinator: ContactorScreenCoordinator = rememberContactorScreenCoordinator(),
) {
  // State observing and declarations
  val uiState by coordinator.screenStateFlow.collectAsState(ContactorScreenState())

  // UI Actions
  val actions = rememberContactorScreenActions(coordinator)

  // UI Rendering
  ContactorScreenScreen(uiState, actions)
}


@Composable
fun rememberContactorScreenActions(coordinator: ContactorScreenCoordinator): ContactorScreenActions {
  return remember(coordinator) {
    ContactorScreenActions(
      onClick = coordinator::doStuff
    )
  }
}
