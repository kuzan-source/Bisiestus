package com.espiralsoft.bisiestus.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.espiralsoft.bisiestus.presentation.states.TimerUnit

@Composable
fun HorizontalTimerContent(units: List<TimerUnit>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        units.forEachIndexed { index, unit ->
            if (index > 0) {
                TimeSeparator()
            }
            TimeBlock(
                value = unit.value,
                label = unit.label
            )
        }
    }
}
