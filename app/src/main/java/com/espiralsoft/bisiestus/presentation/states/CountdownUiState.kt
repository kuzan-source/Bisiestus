package com.espiralsoft.bisiestus.presentation.states

data class CountdownUiState(
    val weeks: Long = 0,
    val days: Long = 0,
    val hours: Long = 0,
    val minutes: Long = 0,
    val seconds: Long = 0,
    val isFeb29: Boolean = false,
    val progressionColor: Float = 0f
)
