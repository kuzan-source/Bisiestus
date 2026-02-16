package com.espiralsoft.bisiestus.domain.model

import java.time.LocalDateTime

sealed interface CountdownTarget {
    data object IsFeb29 : CountdownTarget
    data class Target(val dateTime: LocalDateTime) : CountdownTarget
}
