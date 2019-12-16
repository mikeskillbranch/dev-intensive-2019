package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        fullName ?: return Pair(null, null)
        if (fullName.trim().isEmpty()) {
            return Pair(null, null)
        }
        val parts = fullName.split(" ")
        val firstName = parts.getOrNull(0)
        val lastName = parts.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName == null && lastName == null) {
            return null
        }
        var firstLetter: Char? = null
        var secondLetter: Char? = null
        if (!(firstName?.trim().isNullOrEmpty())) {
            firstLetter = firstName?.get(0)
        }
        if (!(lastName?.trim().isNullOrEmpty())) {
            secondLetter = lastName?.get(0)
        }
        if (firstLetter == null && secondLetter == null) {
            return null
        }
        firstLetter = firstLetter?.toUpperCase()
        secondLetter = secondLetter?.toUpperCase()
        if (firstLetter == null) {
            return secondLetter.toString()
        }
        if (secondLetter == null) {
            return firstLetter.toString()
        }
        return String(charArrayOf(firstLetter, secondLetter))
    }

    fun transliteration(payload: String?, divider: String = " "): String? {
        payload ?: return null
//        val parts = payload.split(" ")
        val parts = payload.split(Regex("[\\s]|[$divider]|[_]"))
        var fName = parts.getOrNull(0)
        var sName = parts.getOrNull(1)
        val transFName = mutableListOf<String>()
        val transSName = mutableListOf<String>()

        if (fName != null) {
            var i = 0
            for (letter in fName) {
                if (i == 0) {
                    transFName.add(getLetterString(letter, true))
                } else {
                    transFName.add(getLetterString(letter, false))
                }
                i++
            }
        } else {
            fName = ""
        }
        if (sName != null) {
            var i = 0
            for (letter in sName) {
                if (i == 0) {
                    transSName.add(getLetterString(letter, true))
                } else {
                    transSName.add(getLetterString(letter, false))
                }
                i++
            }
        } else {
            sName = ""
        }
        val fullName = StringBuilder()
        for (letter in transFName) {
            fullName.append(letter)
        }
        fullName.append(divider)
        for (letter in transSName) {
            fullName.append(letter)
        }
        return fullName.toString()
    }

    private fun getLetterString(letter: Char, isFirst: Boolean): String {
        val letLow = letter.toLowerCase()
        val letLowStr = letLow.toString()
        var newLet: String = String()
        if (liters.containsKey(letLowStr)) {
            newLet = liters.get(letLowStr)!!
            if (isFirst) {
                var i = 0
                val sb = StringBuilder()
                for (l in newLet) {
                    if (i == 0) {
                        sb.append(l.toUpperCase())
                    } else {
                        sb.append(l)
                    }
                    i++
                }
                return sb.toString()
            } else {
                return newLet
            }

        } else {
            return letter.toString()
        }
    }

    val liters = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )
}