package com.espiralsoft.bisiestus.presentation.states

data class CountdownUiState(
    val weeks: Int = 0,
    val days: Int = 0,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val isFeb29: Boolean = false,
    val progressionColor: Float = 0f
)
