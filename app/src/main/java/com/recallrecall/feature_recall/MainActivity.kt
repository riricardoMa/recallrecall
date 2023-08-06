package com.recallrecall.feature_recall

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.recallrecall.feature_recall.presentation.conversation.ConversationScreen
import com.recallrecall.feature_recall.presentation.messages.MessageScreen
import com.recallrecall.feature_recall.presentation.util.Screen
import com.recallrecall.feature_recall.service.GuardNotificationListenerService
import com.recallrecall.ui.theme.RecallRecallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // 判断是否开启监听通知权限
    if (NotificationManagerCompat.getEnabledListenerPackages(this).contains(getPackageName())) {
      Log.i("noti", "enabled")
      val serviceIntent = Intent(this, GuardNotificationListenerService::class.java)
      startService(serviceIntent)
    } else {
      Log.i("noti", "not enabled")
      // 去开启 监听通知权限
      startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
    }
    setContent {
      MaterialTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = Screen.MessageScreen.route) {
            composable(route = Screen.MessageScreen.route) {
              MessageScreen(navController = navController)
            }
            composable(route = Screen.ConversationScreen.route + "?name={name}",
              arguments = listOf(
                navArgument(name = "name") {
                  type = NavType.StringType
                  defaultValue = ""
                }
              )
            ) {
              ConversationScreen(navController = navController)
            }
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  RecallRecallTheme { Greeting("Android") }
}
