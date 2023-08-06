package com.recallrecall.feature_recall.presentation.messages

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.presentation.messages.components.MessageItem
import com.recallrecall.feature_recall.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(navController: NavController, viewModel: MessageViewModel = hiltViewModel()) {

  val scope = rememberCoroutineScope()
  val lazyPagingNames = viewModel.names.collectAsLazyPagingItems()

  Scaffold(topBar = { TopAppBar(title = { Text(text = "Messages") }) }) { padding ->
    LazyColumn(modifier = Modifier.padding(padding), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      items(count = lazyPagingNames.itemCount) { index ->
        lazyPagingNames[index]?.let {
          val message by viewModel.getMessageByName(name = it).collectAsState(initial = Message())
          MessageItem(
              modifier = Modifier.padding(horizontal = 12.dp),
              title = message.name,
              content = message.content,
              date = message.date,
              color = message.recalled.getColor(),
            ) {
              navController.navigate(route = Screen.ConversationScreen.route + "?name=${message.name}")
          }
        }
      }
    }
  }
}

@Composable
fun Boolean.getColor(): Color {
  return if (!this) {
    Color(parseColor("#B9F6CA"))
  } else {
    MaterialTheme.colorScheme.error
  }
}

@Preview(name = "MessageScreen")
@Composable
private fun PreviewMessageScreen() {
  MessageScreen(navController = rememberNavController())
}
