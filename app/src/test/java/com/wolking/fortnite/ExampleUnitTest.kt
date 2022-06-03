package com.wolking.fortnite

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val data = listOf(10, 15,18, 30, 54,60, 90)

    // 10,15,18
    //54,90,60

    @Test
    fun filter() {
        val numbersUp = data.filter { it < 30 }
        val numbersDown = data.filter { it > 50 }

        val list = ArrayList<Int>()
        list.addAll(numbersUp.take(3))
        list.addAll(numbersDown.take(3))

        var med = 0.0
        list.forEach {
            med += it
        }


        val total = med / list.size
        assert(total.toInt()  == 41)
    }
}