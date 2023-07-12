package com.recallrecall.ui.contactorscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/** UI State that represents ContactorScreenScreen */
class ContactorScreenState

/** ContactorScreen Actions emitted from the UI Layer passed to the coordinator to handle */
data class ContactorScreenActions(
    val onClick: () -> Unit = {},
)

/** Compose Utility to retrieve actions from nested components */
val LocalContactorScreenActions =
    staticCompositionLocalOf<ContactorScreenActions> {
      error("{NAME} Actions Were not provided, make sure ProvideContactorScreenActions is called")
    }

@Composable
fun ProvideContactorScreenActions(
    actions: ContactorScreenActions,
    content: @Composable () -> Unit,
) {
  CompositionLocalProvider(LocalContactorScreenActions provides actions) { content.invoke() }
}
