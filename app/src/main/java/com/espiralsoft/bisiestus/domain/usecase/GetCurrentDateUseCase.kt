package com.espiralsoft.bisiestus.domain.usecase

import java.time.LocalDateTime
import com.espiralsoft.bisiestus.data.repository.DateLocalImpl
import com.espiralsoft.bisiestus.domain.repository.DateRepository

class GetCurrentDateUseCase(
    private val dateRepository: DateRepository = DateLocalImpl()
) {
    operator fun invoke(): LocalDateTime {
        return dateRepository.now()
    }
}
