package com.recallrecall.feature_recall.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.SpannableString
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.recallrecall.feature_recall.domain.repository.MessageRepository
import com.recallrecall.feature_recall.domain.use_case.WeChatUseCases
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GuardNotificationListenerService : NotificationListenerService() {

  private val wechatPkg = "com.tencent.mm"

  @Inject lateinit var repository: MessageRepository

  @Inject lateinit var weChatUseCases: WeChatUseCases

  private val serviceJob = Job()
  private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

  companion object {
    val TAG = GuardNotificationListenerService::class.java.simpleName
    private val CHANNEL_ID = "WeChatService"
    private val CHANNEL_NAME = "Wechat"
  }

  override fun onNotificationPosted(sbn: StatusBarNotification) {
    serviceScope.launch { processMassage(sbn) }
    super.onNotificationPosted(sbn)
  }

  override fun onDestroy() {
    super.onDestroy()

    // Cancel all the coroutines when the service is destroyed
    serviceJob.cancel()
  }

  private suspend fun processMassage(sbn: StatusBarNotification) {
    val packageName = sbn.packageName
    if (packageName != wechatPkg) return
    sbn.notification.extras?.let { extras ->
      val title = extras.getString(Notification.EXTRA_TITLE) ?: return
      val msgText: Any? = extras.getCharSequence(Notification.EXTRA_TEXT)
      if (msgText is SpannableString) {
        Log.d(TAG, "is SpannableString ...." + msgText.subSequence(0, msgText.length))
      } else {
        saveMsg(packageName, title, msgText.toString())
      }
    }
  }

  private suspend fun saveMsg(pkgName: String, title: String, text: String) {
    if (pkgName != wechatPkg) return

    val sdf = SimpleDateFormat("yyyy MM/dd hh:mm:ss", Locale.US)
    val currentDate = sdf.format(Date())
    if (isMessageRecalled(text)) {
      weChatUseCases.updateToRecalled.invoke(currentDate, title)
      sendNotification(name = title)
    } else {
      weChatUseCases.addNotification.invoke(
          title = title,
          text = text,
          date = currentDate,
      )
    }
  }

  private fun isMessageRecalled(text: String): Boolean {
    return text.contains("撤回了一条消息") || text.contains("撤回一条消息")
  }

  private fun sendNotification(name: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val importance = NotificationManager.IMPORTANCE_HIGH
      val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
      channel.description = "description"
      val notificationManager = this.getSystemService(NotificationManager::class.java)
      notificationManager.createNotificationChannel(channel)
    }
    val builder: NotificationCompat.Builder =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle("$name 撤回了一条信息")
            .setContentText("点击进入APP内查看")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
    val notificationManager = NotificationManagerCompat.from(this)
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
        PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return
    }
    notificationManager.notify(Random().nextInt(10000), builder.build())
  }
}
