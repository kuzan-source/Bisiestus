package com.espiralsoft.bisiestus.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.states.TimerUnit

@Composable
fun VerticalTimerContent(units: List<TimerUnit>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        units.forEach { unit ->
            TimeBlock(
                value = unit.value,
                label = unit.label
            )
        }
    }
}
