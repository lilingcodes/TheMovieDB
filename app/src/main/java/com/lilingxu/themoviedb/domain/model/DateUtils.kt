package com.lilingxu.themoviedb.domain.model

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("ConstantLocale")
val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())


fun SimpleDateFormat.parseOrNull(date: String?): Date? {
    if (date == null || date.isEmpty()) return null
    return try {
        this.parse(date)
    } catch (e: ParseException) {
        null
    }
}
fun dateText(date: Date) :String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
}

fun formatMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}h ${remainingMinutes}min"
}