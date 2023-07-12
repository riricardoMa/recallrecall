package com.recallrecall.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.SpannableString
import android.util.Log
import com.recallrecall.db.Message
import com.recallrecall.repository.MessageRepository
import com.recallrecall.repository.RoomMessageRepository
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class GuardNotificationListenerService : NotificationListenerService() {

  private val wechatPkg = "com.tencent.mm"

  @Inject
  lateinit var repository: RoomMessageRepository

  companion object {
    val TAG = GuardNotificationListenerService::class.java.simpleName
  }

  override fun onNotificationPosted(sbn: StatusBarNotification) {
    showMsg(sbn)
  }

  private fun showMsg(sbn: StatusBarNotification) {
    val packageName = sbn.packageName
    if (packageName != wechatPkg) return
    sbn.notification.extras?.let {extras ->
      val title = extras.getString(Notification.EXTRA_TITLE)
      val msgText: Any? = extras.getCharSequence(Notification.EXTRA_TEXT)
      if (msgText is SpannableString) {
        Log.d(TAG, "is SpannableString ...." + msgText.subSequence(0, msgText.length))
      } else {
        saveMsg(packageName, title, msgText.toString())
      }
    }
  }

  private fun saveMsg(pkgName: String, title: String?, text: String?) {
    if (pkgName != wechatPkg) return

    val sdf = SimpleDateFormat("yyyy MM/dd hh:mm:ss")
    val currentDate = sdf.format(Date())

    WeChatService(this, repository).addNotification(title = title, text = text, date = currentDate,)
  }
}
