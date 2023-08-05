package com.recallrecall.feature_recall.domain.use_case

import com.recallrecall.feature_recall.domain.repository.MessageRepository

class UpdateToRecalled(private val repository: MessageRepository) {
    suspend operator fun invoke(date: String, title: String) {
        // yyyy MM/dd hh:mm:ss
        val startDate = getStartDate(date)
        val messages = repository.loadByDateAndName(startDate, date, title)
        messages?.forEach { message ->
            repository.update(message.copy(recalled = true))
        }
    }

    private fun getStartDate(date: String?): String? {
        if (date == null) return null
        var min: StringBuilder = StringBuilder()
        var res = ""
        min.append(date[14]).append(date[15])
        var temp = (min.toString().toInt()) - 2

        // Todo: fix the situation that temp < 0 which need to cause hour to change as well
        if (temp < 0) temp = 0

        if (temp < 10) {
            res = date.substring(0, 14) + "0" + temp.toString() + date.substring(16)
        } else {
            res = date.substring(0, 14) + temp.toString() + date.substring(16)
        }

        return res
    }
}
