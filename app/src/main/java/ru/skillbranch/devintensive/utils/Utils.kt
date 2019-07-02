package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val partsOfName: List<String>? = if (!fullName.isNullOrBlank() && !fullName.isNullOrEmpty())
            fullName.split(" ")
        else null

        val firstName = partsOfName?.getOrNull(0)
        val lastName = partsOfName?.getOrNull(1)
        return firstName to lastName
    }

    fun parseFullName(fullName: String?, clear: Boolean) : Pair<String?, String?> {
        val partsOfName: List<String>?
        if (!fullName.isNullOrBlank() && !fullName.isNullOrEmpty())
            partsOfName = fullName.split(" ")
        else if (!clear) partsOfName = "Ai Bolit".split(" ")
        else partsOfName = null

        val firstName = partsOfName?.getOrNull(0)
        val lastName = partsOfName?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstWord = if (!firstName.isNullOrBlank() && !firstName.isNullOrEmpty())
         firstName.first()
        else null
        val secondWord = if (!lastName.isNullOrBlank() && !lastName.isNullOrEmpty())
            lastName.first()
        else null

        val initials: String? = if (firstWord == null && secondWord == null) null
        else if (secondWord == null) firstWord.toString()
        else firstWord?.toString()?.plus(secondWord) ?: secondWord.toString()

        return initials?.toUpperCase()
    }

    fun transliteration(payload: String?, divider: String? = " ") : String? {
        val translitMap = mapOf(
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
        val englishWords = "ABCDEFGHIKLMNOPQRSTVWXYZ"

        val sb = StringBuilder()
        payload?.forEach { payloadChar ->
            if (!payloadChar.isUpperCase()) {
                if (payloadChar.isWhitespace() || payloadChar == '_' || payloadChar == "$divider".toCharArray().first())
                    sb.append(divider)

                englishWords.forEach {
                    if (it.toString().toLowerCase() == payloadChar.toString().toLowerCase()) sb.append(it.toLowerCase())
                }

                translitMap.keys.forEach {
                    if (it == payloadChar.toString().toLowerCase()) sb.append(translitMap.getValue(it))
                }
            } else {
                englishWords.forEach {
                    if (it.toString().toLowerCase() == payloadChar.toString().toLowerCase()) sb.append(it.toUpperCase())
                }
                translitMap.keys.forEach {key ->
                    if (key == payloadChar.toString().toLowerCase()) {
                        if (translitMap.getValue(key).length > 1) {
                            translitMap.getValue(key).forEach {
                                if (it == translitMap.getValue(key).first())
                                    sb.append(it.toUpperCase())
                                else sb.append(it)
                            }
                        } else sb.append(translitMap.getValue(key).toUpperCase())
                    }
                }
            }
        }

        return if (payload == null) null
        else sb.toString()
    }
}