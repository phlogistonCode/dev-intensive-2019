package ru.skillbranch.devintensive.extensions

fun String.stripHtml(): String {
    var tag = false
    var whitespace = false

    val sb = StringBuilder()
    this.forEach {
        if (it == '<' || tag) {
            tag = true
        }

        if (it.isWhitespace() && whitespace) {
        } else if (it.isWhitespace()) {
            if (!tag) sb.append(it)
            whitespace = true
        } else {
            if (!tag) sb.append(it)
            whitespace = false
        }

        if (it == '>') {
            tag = false
        }

    }
    return sb.toString()
}

fun String.truncate(num: Int = 16): String {
    val result = this.trim()
    return when {
        result.length <= num + 1 -> result
        else -> "${result.take(num + 1).trimEnd()}..."
    }
}
