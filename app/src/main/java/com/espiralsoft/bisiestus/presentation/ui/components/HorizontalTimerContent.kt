package com.espiralsoft.bisiestus.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.espiralsoft.bisiestus.presentation.states.CountdownUiState

@Composable
fun HorizontalTimerContent(units: CountdownUiState) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        TimeBlock(
            value = units.weeks.toString(),
            label = "Semanas"
        )
        TimeSeparator()
        TimeBlock(
            value = units.days.toString(),
            label = "Dias"
        )
        TimeSeparator()
        TimeBlock(
            value = units.hours.toString(),
            label = "Horas"
        )
        TimeSeparator()
        TimeBlock(
            value = units.minutes.toString(),
            label = "Minutos"
        )
        TimeSeparator()
        TimeBlock(
            value = units.seconds.toString(),
            label = "Segundos"
        )
    }
}
