package ru.skillbranch.devintensive.extensions

fun String.stripHtml() : String {
    var tag = false
    var whitespace = false

    val sb = StringBuilder()
    this.forEach {
        if (it == '<' || tag) {
            tag = true
        }

        if (it.isWhitespace() && whitespace) {
        } else if (it.isWhitespace()){
            if (!tag) sb.append(it)
            whitespace = true
        } else {
            if (!tag) sb.append(it)
            whitespace = false
        }
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

        if (it == '>') {
            tag = false
    return text.toString()
}
        }
    }

    return sb.toString()
}