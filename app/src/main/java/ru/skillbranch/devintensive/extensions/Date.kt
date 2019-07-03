package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    val time = this.time
    val difference = date.time - time
    val difSeconds = (difference / SECOND).absoluteValue
    val difMinutes = (difference / MINUTE).absoluteValue
    val difHours = (difference / HOUR).absoluteValue
    val difDays = (difference / DAY).absoluteValue
    val message: String

    val textMinutes = when {
        difMinutes in 5..20L -> "минут"
        difMinutes % 10 == 1L -> "минуту"
        difMinutes % 10 in 2..4L -> "минуты"
        else -> "минут"
    }

    val textHours = when {
        difHours in 5..20L -> "часов"
        difHours % 10 == 1L -> "час"
        difHours % 10 in 2..4L -> "часа"
        else -> "часов"
    }

    val textDays = when {
        difDays in 2..20L -> "дней"
        difDays % 10 == 1L -> "день"
        difDays % 10 in 2..4L -> "дня"
        else -> "дней"
    }

    if (difference >= 0) { //Прошло
        message = when {
            difSeconds <= 1 -> "только что"
            difSeconds <= 45 -> "несколько секунд назад"
            difSeconds <= 75 -> "минуту назад"
            difMinutes <= 45 -> "$difMinutes $textMinutes назад"
            difMinutes <= 75 -> "час назад"
            difHours <= 22 -> "$difHours $textHours назад"
            difHours <= 26 -> "день назад"
            difDays <= 360 -> "$difDays $textDays назад"
            difDays > 360 -> "более года назад"
            else -> "Прошло неизвестное кол-во времени"
        }
    } else { //Будет
        message = when {
            difSeconds <= 1 -> "только что"
            difSeconds <= 45 -> "через несколько секунд"
            difSeconds <= 75 -> "через минуту"
            difMinutes <= 45 -> "через $difMinutes $textMinutes"
            difMinutes <= 75 -> "через час"
            difHours <= 22 -> "через $difHours $textHours"
            difHours <= 26 -> "через день"
            difDays <= 360 -> "через $difDays $textDays"
            difDays > 360 -> "более чем через год"
            else -> "Через неизвестное кол-во времени"
        }
    }

    return message
}

enum class TimeUnits {
    SECOND{
        fun plural(value:Int) : String {
            val text = when {
                value in 5..20 -> "секунд"
                value % 10 == 1 -> "секунду"
                value % 10 in 2..4 -> "секунды"
                else -> "секунд"
            }
            return "$value $text"
        }
    },
    MINUTE{
        fun plural(value:Int) : String {
            val text = when {
                value in 5..20 -> "минут"
                value % 10 == 1 -> "минуту"
                value % 10 in 2..4 -> "минуты"
                else -> "минут"
            }
            return "$value $text"
        }
    },
    HOUR{
        fun plural(value:Int) : String {
            val text = when {
                value in 5..20 -> "часов"
                value % 10 == 1 -> "час"
                value % 10 in 2..4 -> "часа"
                else -> "часов"
            }
            return "$value $text"
        }
    },
    DAY{
        fun plural(value:Int) : String {
            val text = when {
                value in 5..20 -> "дней"
                value % 10 == 1 -> "день"
                value % 10 in 2..4 -> "дня"
                else -> "дней"
            }
            return "$value $text"
        }
    };
}