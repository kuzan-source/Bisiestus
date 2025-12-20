package com.espiralsoft.bisiestus.presentation.states

import java.time.LocalDateTime

data class TimerStatus(
    // Representa el año -999,999,999-01-01T00:00:00
    val timeNow: LocalDateTime = LocalDateTime.MIN
)
