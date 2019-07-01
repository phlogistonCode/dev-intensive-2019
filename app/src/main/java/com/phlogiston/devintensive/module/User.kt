package com.phlogiston.devintensive.module

import com.phlogiston.devintensive.utils.Utils
import java.util.*

class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {
    init {
        println("My name is $firstName $lastName, and i'm doctor!")
    }

    companion object Factory {
        private var cacheId = -1
        fun makeUser(fullName: String?) : User {
            cacheId++

            val (firstName, lastName) = Utils.parseFullName(fullName, false)

            return User(id = "$cacheId", firstName = firstName, lastName = lastName, avatar = null)
        }
    }
}