package com.espiralsoft.bisiestus.util

import java.time.Year

object LeapYearUtils {

    fun isLeapYear(year: Int): Boolean {
        return Year.isLeap(year.toLong())
    }

    fun nextLeapYear(fromYear: Int): Int {
        var year: Int = fromYear +1
        while (!isLeapYear(year)) {
            year++
        }
        return year
    }

}
