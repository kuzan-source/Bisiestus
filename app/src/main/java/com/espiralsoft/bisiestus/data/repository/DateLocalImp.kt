package com.espiralsoft.bisiestus.data.repository

import java.time.LocalDateTime
import com.espiralsoft.bisiestus.domain.repository.DateRepository

class DateLocalImpl : DateRepository {

    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
