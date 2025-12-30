package com.espiralsoft.bisiestus.domain.usecase

import com.espiralsoft.bisiestus.data.repository.DateLocalImp
import java.time.LocalDateTime
import java.time.Year

class NextLeapYearUseCase (
    private val dataRepository: DateLocalImp = DateLocalImp()
)
{
    suspend operator fun invoke(): LocalDateTime {
        val currentTime: LocalDateTime = dataRepository.getCurrentTime()
        var year: Int = (currentTime.year +1)
        while (!Year.isLeap(year.toLong())) {
            year++
        }
        return LocalDateTime.of(year, 2, 29, 0, 0)
    }
}
