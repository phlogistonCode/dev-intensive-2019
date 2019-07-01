package com.phlogiston.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val partsOfName: List<String>?
        partsOfName = if (!fullName.isNullOrBlank() && !fullName.isNullOrEmpty())
            fullName.split(" ")
        else "Ai Bolit".split(" ")

        val firstName = partsOfName.getOrNull(0)
        val lastName = partsOfName.getOrNull(1)
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
}