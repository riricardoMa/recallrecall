package com.recallrecall.feature_recall.presentation.messages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(navController: NavController, viewModel: MessageViewModel = hiltViewModel()) {

  val scope = rememberCoroutineScope()

  Scaffold(topBar = { TopAppBar(title = { Text(text = "Messages") }) }) { padding ->
    LazyColumn(modifier = Modifier.padding(padding)) {

    }
  }
}

@Preview(name = "MessageScreen")
@Composable
private fun PreviewMessageScreen() {
  MessageScreen(navController = rememberNavController())
}
