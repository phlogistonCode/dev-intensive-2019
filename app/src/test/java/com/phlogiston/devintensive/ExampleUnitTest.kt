package com.phlogiston.devintensive

import com.phlogiston.devintensive.module.User
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_task1() {
        val user1 = User.makeUser("Josef Mengele")
        val user2 = User.makeUser("Gregory House")
        val user3 = User.makeUser("Yuri Zivago")
        val userEmpty = User.makeUser("")
        val userBlank = User.makeUser(" ")
        val userNull = User.makeUser(null)
    }
}
