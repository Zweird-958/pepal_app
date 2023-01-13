package utils

import java.text.SimpleDateFormat
import java.util.*

fun removeOneDay(date: String): String {
    println("TESTDATE=========")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = dateFormat.parse(date)

    println(date)
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.DATE, -1)
    return dateFormat.format(calendar.time)
}