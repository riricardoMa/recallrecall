package com.recallrecall.feature_recall.service
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.content.pm.PackageManager
//import android.os.Build
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import com.recallrecall.feature_recall.domain.model.Message
//import com.recallrecall.feature_recall.domain.repository.MessageRepository
//import java.util.*
//
//class WeChatService(private val context: Context, private val repository: MessageRepository) {
//
//  private val TAG = "Wechat"
//  private val CHANNEL_ID = "WeChatService"
//  private val CHANNEL_NAME = "Wechat"
//
//  @SuppressLint("LongLogTag")
//  fun addNotification(title: String?, text: String?, date: String?) {
//    if (title == null || text == null) return
//
//    // 找到内容左边的 ‘：’的位置，从而找到内容
//    val idx = text.indexOf(title)
//
//    var content = text
//    // 检查是否不是第一条信息
//    if (idx != -1) {
//      content = text.substring(idx + title.length + 2)
//    }
//
//    // 检查是否是撤回消息提醒
//    if (content.contains("撤回了一条消息") || content.contains("撤回一条消息")) {
//      updateToRecalled(date, title)
//      return
//    }
//
//    // 建立message
//    val msg = Message(name = title, content = content, date = date)
//
//    repository.insertAll(msg)
//  }
//
//  fun updateToRecalled(date: String?, title: String?) {
//
//    // yyyy MM/dd hh:mm:ss
//    val startDate = getStartDate(date)
//    if (startDate != null) {
//      Log.d(TAG, "$startDate $date")
//    }
//    val messages = repository?.loadByDateAndName(startDate, date, title)
//    if (messages != null) {
//      if (messages[0] != null) sendNotification(this.context, messages[0]!!)
//
//      for (message in messages) {
//        message?.recalled = true
//        repository.update(message!!)
//      }
//    }
//  }
//
//  private fun getStartDate(date: String?): String? {
//    if (date == null) return null
//    var min: StringBuilder = StringBuilder()
//    var res = ""
//    min.append(date[14]).append(date[15])
//    var temp = (min.toString().toInt()) - 2
//
//    // Todo: fix the situation that temp < 0 which need to cause hour to change as well
//    if (temp < 0) temp = 0
//
//    Log.d(TAG, temp.toString())
//    if (temp < 10) {
//      res = date.substring(0, 14) + "0" + temp.toString() + date.substring(16)
//    } else {
//      res = date.substring(0, 14) + temp.toString() + date.substring(16)
//    }
//
//    Log.d(TAG, res)
//    return res
//  }
//
//  private fun sendNotification(context: Context, message: Message) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//      val importance = NotificationManager.IMPORTANCE_HIGH
//      val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
//      channel.description = "description"
//      val notificationManager = context.getSystemService(NotificationManager::class.java)
//      notificationManager.createNotificationChannel(channel)
//    }
//    val builder: NotificationCompat.Builder =
//        NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(androidx.core.R.drawable.notification_bg)
//            .setContentTitle("${message.name} 撤回了一条信息")
//            .setContentText("点击进入APP内查看")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setAutoCancel(true)
//    val notificationManager = NotificationManagerCompat.from(context)
//    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) !=
//        PackageManager.PERMISSION_GRANTED) {
//      // TODO: Consider calling
//      //    ActivityCompat#requestPermissions
//      // here to request the missing permissions, and then overriding
//      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//      //                                          int[] grantResults)
//      // to handle the case where the user grants the permission. See the documentation
//      // for ActivityCompat#requestPermissions for more details.
//      return
//    }
//    notificationManager.notify(Random().nextInt(10000), builder.build())
//  }
//}
