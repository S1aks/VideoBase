package com.s1aks.videobase.utils

import android.annotation.SuppressLint
import android.graphics.Color
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val Int.getColorFromIntProgress: Int
    get() = when (this) {
        in 0..39 -> Color.parseColor("#FF0000")
        in 40..49 -> Color.parseColor("#FF8000")
        in 50..59 -> Color.parseColor("#FFFF00")
        in 60..69 -> Color.parseColor("#80FF00")
        else -> Color.parseColor("#00FF00")
    }

val String.toFormattedDateText: String
    @SuppressLint("NewApi")
    get() = LocalDate
            .parse(this)
            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))