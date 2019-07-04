package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(num: Int = 16): String {
    val sb = StringBuilder()
    var i = 0
    for (c in this) {
        i++
        if (i <= num)
            sb.append(c)
    }
    var text: CharSequence = sb
    while (sb.last().isWhitespace())
        text = sb.dropLastWhile { it.isWhitespace() }

    sb = text.toString()

    if (this.length > num)
        sb.append("...")

    return text.toString()
}