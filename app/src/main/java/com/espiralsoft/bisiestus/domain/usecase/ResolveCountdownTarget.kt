package com.espiralsoft.bisiestus.domain.usecase

import java.time.LocalDate
import java.time.LocalDateTime
import com.espiralsoft.bisiestus.domain.model.CountdownTarget
import com.espiralsoft.bisiestus.util.LeapYearUtils

class ResolveCountdownTarget {

    fun execute(now: LocalDateTime): CountdownTarget {

        val today: LocalDate = now.toLocalDate()
        val year: Int = today.year

        //Es año bisiesto y...
        if (LeapYearUtils.isLeapYear(year)) {

            val feb29: LocalDate = LocalDate.of(year, 2, 29)

            return when {
                today.isBefore(feb29) -> {
                    //El now esta antes del 29Feb
                    CountdownTarget.Target(feb29.atStartOfDay())
                }

                today.isEqual(feb29) -> {
                    //El now es igual a 29Feb
                    CountdownTarget.IsFeb29
                }

                else -> {
                    //La Fecha es despues del 29Feb
                    val nextLeap: Int = LeapYearUtils.nextLeapYear(year)
                    CountdownTarget.Target(
                        LocalDate.of(nextLeap, 1, 1).atStartOfDay()
                    )
                }
            }
        }

        //NO es año bisiesto y la fecha es despues del 29Feb
        val nextLeap: Int = LeapYearUtils.nextLeapYear(year)
        return CountdownTarget.Target(
            LocalDate.of(nextLeap, 1, 1).atStartOfDay()
        )
    }

}
