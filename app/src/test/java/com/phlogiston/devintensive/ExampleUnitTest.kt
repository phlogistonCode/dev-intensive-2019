package com.phlogiston.devintensive

import com.phlogiston.devintensive.extensions.TimeUnits
import com.phlogiston.devintensive.extensions.add
import com.phlogiston.devintensive.extensions.format
import com.phlogiston.devintensive.extensions.humanizeDiff
import com.phlogiston.devintensive.models.*
import com.phlogiston.devintensive.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

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

    @Test
    fun test_task2() {
        val user1 = User.makeUser("Josef Mengele")
        val textMessage = BaseMessage.makeMessage(user1, Chat("0"), payload = "Hello, i'm ${user1.firstName}", type = "text")
        val imageMessage = BaseMessage.makeMessage(user1, Chat("0"), payload = "My avatar: ${user1.avatar}", type = "image")

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }

    @Test
    fun test_task3() {
        println(Utils.parseFullName(null))
        println(Utils.parseFullName(""))
        println(Utils.parseFullName(" "))
        println(Utils.parseFullName("John"))
    }

    @Test
    fun test_task4_5() {
        val user1 = User.makeUser("Josef Mengele")
        val user2 = user1.copy(lastVisit = Date())
        val user3 = user1.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user4 = user1.copy(lastName = "Kessel", lastVisit = Date().add(2, TimeUnits.MINUTE))

        println("""
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun test_task6() {
        println(Utils.toInitials("john" ,"doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun test_task7() {
        println(Utils.transliteration("Стереотипов")) //Stereotipov
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр","_")) //Amazing_Petr
        println(Utils.transliteration("Amazing Петр Евгений Много Слов ин English Words TestThis","_")) //Amazing_Petr
        println(Utils.transliteration(" ","_")) //_
        println(Utils.transliteration("","_")) //
        println(Utils.transliteration(null,"_")) //
    }

    @Test
    fun test_task8() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год

    }
}
