package com.espiralsoft.bisiestus.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.states.CountdownUiState

@Composable
fun VerticalTimerContent(units: CountdownUiState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TimeBlock(
            value = units.days.toString(),
            label = "Dias"
        )
        TimeBlock(
            value = units.hours.toString(),
            label = "Horas"
        )
        TimeBlock(
            value = units.minutes.toString(),
            label = "Minutos"
        )
        TimeBlock(
            value = units.seconds.toString(),
            label = "Segundos"
        )
    }
}
