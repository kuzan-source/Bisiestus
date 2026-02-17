package com.espiralsoft.bisiestus.domain.repository

import java.time.LocalDateTime

interface DateRepository {
    fun now(): LocalDateTime
}
