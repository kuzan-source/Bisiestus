package com.espiralsoft.bisiestus.domain.usecase

import com.espiralsoft.bisiestus.data.repository.DateLocalImp
import java.time.Year

class ValidateYearUseCase (
    private val dataRepository: DateLocalImp = DateLocalImp()
)
{
    suspend operator fun invoke(): Boolean {
        val currentYear: Int = dataRepository.getCurrentTime().year
        return Year.isLeap(currentYear.toLong())
    }
}
