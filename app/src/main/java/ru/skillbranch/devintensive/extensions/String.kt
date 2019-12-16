package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(size: Int = 16): String {
    val sb = StringBuilder(this)
    val length = this.length
    println("length $length size $size")
    if (length > size) {
        sb.delete(size, length)
        var i = size - 1
        while (i > 0) {
            if (sb[i] == ' ') {
//            if (sb[i].isWhitespace()) {
                sb.delete(i, i + 1)
            } else {
                break
            }
            i--
        }

        sb.append("...")
        return sb.toString()
    } else {
        return this
    }
}


fun String.stripHtml(): String {
    var stringWithoutHtml = this.replace(Regex("\\<[^>]*>"), "")
    stringWithoutHtml = stringWithoutHtml.replace(Regex("[\\s]{2,}"), " ")
    return stringWithoutHtml
}