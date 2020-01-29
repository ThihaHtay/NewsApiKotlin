package com.example.newsapikotlin

import java.text.SimpleDateFormat
import java.util.*

fun toSimpleString(publishedAt : String): String {
    val inputFormatter = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat(
        "EEE, dd MMM yyyy HH:mm",
        Locale.getDefault()     //to get time from devices
    )
    val date = inputFormatter.parse(publishedAt)
    return outputFormatter.format(date)

   }