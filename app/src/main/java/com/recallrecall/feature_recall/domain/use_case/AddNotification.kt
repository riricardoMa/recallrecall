package com.recallrecall.feature_recall.domain.use_case

import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.domain.repository.MessageRepository

class AddNotification(private val repository: MessageRepository) {

  suspend operator fun invoke(title: String?, text: String?, date: String?) {
    if (title == null || text == null) return

    // 找到内容左边的 ‘：’的位置，从而找到内容
    val idx = text.indexOf(title)

    var content = text
    // 检查是否不是第一条信息
    if (idx != -1) {
      content = text.substring(idx + title.length + 2)
    }

    //        // 检查是否是撤回消息提醒
    //        if (content.contains("撤回了一条消息") || content.contains("撤回一条消息")) {
    //            updateToRecalled(date, title)
    //            return
    //        }

    // 建立message
    val msg = Message(name = title, content = content, date = date.orEmpty())

    repository.insertAll(msg)
  }
}
