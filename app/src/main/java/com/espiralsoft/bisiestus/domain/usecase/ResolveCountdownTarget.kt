package com.espiralsoft.bisiestus.domain.usecase


import java.time.LocalDateTime
import com.espiralsoft.bisiestus.domain.model.CountdownTarget
import com.espiralsoft.bisiestus.util.LeapYearUtils


class ResolveCountdownTarget {

    fun execute(now: LocalDateTime): CountdownTarget {
        val year: Int = now.year

        if( LeapYearUtils.isLeapYear(year) )
        {
            //Es año bisiesto y...
            val leapDate: LocalDateTime = LocalDateTime.of(year, 2, 29, 0, 0)
            val position: Int = now.compareTo(leapDate)
            if (position < 0)
            {
                //El now esta antes del 29Feb
                return CountdownTarget.Target(leapDate)
            }
            else if(position == 0)
            {
                //El now es igual a 29Feb
                return CountdownTarget.IsFeb29
            }
        }
        //La Fecha es despues del 29Feb
        val nextLeapYear: Int = LeapYearUtils.nextLeapYear(year)
        return CountdownTarget.Target(LocalDateTime.of(nextLeapYear, 1, 1, 0, 0))
    }

}
