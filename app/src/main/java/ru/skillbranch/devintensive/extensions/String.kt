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
    val str = this
    println("str $str")
    val regex = Regex("<.*>")
    val regex2 = Regex("title")

    val zx = str.replace(Regex("<.*>"), "")
    println("zx $zx")
    val we = Regex("""<.*>""").replace(str, "")
    println("we $we")

    val oldStr = this
    val str1 = oldStr.replace(Regex("</.*>"), "")
    val str2 = str1.replace(Regex("<.*>"), "")
    println("str2 $str2")
    var lengthOld = str2.length
    var lengthNew = 0
    var str3 = str2
    while (lengthOld != lengthNew) {
        lengthOld = str3.length

//        str3 = str3.replace("""  """," ")
        str3 = str3.replace(Regex("\\s+")," ")
        lengthNew = str3.length
    }
    println("str3 $str3")
return str3
}