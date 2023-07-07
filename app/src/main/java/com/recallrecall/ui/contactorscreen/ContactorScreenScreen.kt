package com.recallrecall.ui.contactorscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContactorScreenScreen(
  state: ContactorScreenState = ContactorScreenState(),
  actions: ContactorScreenActions = ContactorScreenActions(),
) {
  Text(
    text = "Hello world!",
  )
}

@Composable
@Preview(name = "ContactorScreen")
private fun ContactorScreenScreenPreview() {
  ContactorScreenScreen()
}

