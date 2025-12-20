package com.espiralsoft.bisiestus.domain.usecase

import com.espiralsoft.bisiestus.data.repository.DateLocalImp
import java.time.LocalDateTime

class GetCurrentDateUseCase (
    private val dataRepository: DateLocalImp = DateLocalImp()
)
{
    suspend operator fun invoke(): LocalDateTime {
        return dataRepository.getCurrentTime()
    }
}
