package com.lilingxu.themoviedb.domain.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("ConstantLocale")
val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun dateText(date: Date) :String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
}

fun formatMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}h ${remainingMinutes}min"
}