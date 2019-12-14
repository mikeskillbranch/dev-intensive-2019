package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val str = when (this) {
            SECOND -> {
                if (value in 11..19) {
                    "секунд"
                } else {
                    when (value % 10) {
                        1 -> "секунду"
                        2, 3, 4 -> "секунды"
                        else -> "секунд"
                    }
                }
            }
            MINUTE ->
                if (value in 11..19) {
                    "минут"
                } else {
                    when (value % 10) {
                        1 -> "минуту"
                        2, 3, 4 -> "минуты"
                        else -> "минут"
                    }
                }

            HOUR ->
                if (value in 11..19) {
                    "часов"
                } else {
                    when (value % 10) {
                        1 -> "час"
                        2, 3, 4 -> "часа"
                        else -> "часов"
                    }
                }
            DAY ->
                if (value in 11..19) {
                    "дней"
                } else {
                    when (value % 10) {
                        1 -> "день"
                        2, 3, 4 -> "дня"
                        else -> "дней"
                    }
                }
        }
        return "$value $str"
    }
}

fun Date.humanizeDiff(): String {
    val sec = this.time / 1000 - Date().time / 1000
    if (sec <= 0) {
        val curSec = sec * (-1)
        val message = when (curSec) {
            in 0L..1L -> "только что"
            in 1L..45L -> "несколько секунд назад"
            in 45L..75L -> "минуту назад"
            in 75L..45L * 60 -> "${TimeUnits.MINUTE.plural((curSec / 60).toInt())} назад"
            in 45L * 60..75L * 60 -> "час назад"
            in 75L * 60..22L * 60 * 60 -> "${TimeUnits.HOUR.plural((curSec / 60 / 60).toInt())} назад"
            in 22L * 60 * 60..26L * 60 * 60 -> "день назад"
            in 26L * 60 * 60..360L * 24 * 60 * 60 -> "${TimeUnits.DAY.plural((curSec / 60 / 60 / 24).toInt())} назад"
            in 360L * 24 * 60 * 60..Long.MAX_VALUE -> "более года назад"
            else -> ""
        }
//        println(message)
        return message
    } else {
        val message = when (sec) {
            in 0L..1L -> "только что"
            in 1L..45L -> "через несколько секунд"
            in 45L..75L -> "через минуту"
            in 75L..45L * 60 -> "через ${TimeUnits.MINUTE.plural((sec / 60).toInt())}"
            in 45L * 60..75L * 60 -> "через час"
            in 75L * 60..22L * 60 * 60 -> "через ${TimeUnits.HOUR.plural((sec / 60 / 60).toInt())}"
            in 22L * 60 * 60..26L * 60 * 60 -> "через день"
            in 26L * 60 * 60..360L * 24 * 60 * 60 -> "через ${TimeUnits.DAY.plural((sec / 60 / 60 / 24).toInt())}"
            in 360L * 24 * 60 * 60..Long.MAX_VALUE -> "более чем через год"
            else -> ""
        }
//        println(message)
        return message
    }
}
