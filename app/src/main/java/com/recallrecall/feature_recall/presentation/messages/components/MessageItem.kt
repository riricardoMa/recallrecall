package com.recallrecall.feature_recall.presentation.messages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    date: String,
    color: Color,
    onClick: () -> Unit,
) {
  val horizontalPadding = 12.dp
  Card(
      onClick = onClick,
      modifier = modifier,
      colors =
          CardDefaults.cardColors(
              containerColor = color,
              contentColor = Color.Black,
          ),
  ) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
          Text(text = title, style = MaterialTheme.typography.headlineMedium, maxLines = 1)
          Text(text = date, style = MaterialTheme.typography.bodyLarge, maxLines = 1)
        }
    Text(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        text = content,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
  }
}

@Preview(name = "ConversationRow")
@Composable
private fun PreviewConversationRow() {
  MaterialTheme {
    MessageItem(
        title = "title",
        content = "something",
        date = "3/21",
        color = MaterialTheme.colorScheme.background,
        onClick = {},
    )
  }
}