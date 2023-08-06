package com.recallrecall.feature_recall.presentation.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(
    navController: NavController,
    viewModel: ConversationViewModel = hiltViewModel()
) {
  Scaffold(
      topBar = {
        TopAppBar(
            title = { Text(text = viewModel.messagenerName) },
            navigationIcon = {
              IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
              }
            },
        )
      },
  ) { padding ->
    LazyColumn(
        modifier = androidx.compose.ui.Modifier.padding(padding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
      ) {

      

    }
  }
}

@Preview(name = "ConversationScreen")
@Composable
private fun PreviewConversationScreen() {
  ConversationScreen(navController = rememberNavController())
}
