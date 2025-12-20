package com.espiralsoft.bisiestus.presentation.states

import java.time.LocalDateTime

data class TimerStatus(
    val timeNow: LocalDateTime = LocalDateTime.now()
)
