package com.espiralsoft.bisiestus.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.components.TimeBlock
import com.espiralsoft.bisiestus.presentation.components.TimeSeparator
import com.espiralsoft.bisiestus.presentation.components.TimerLayout
import com.espiralsoft.bisiestus.presentation.viewModel.TimerViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = TimerViewModel(),
){
    // Obtenemos el estado del ViewModel
    val state by viewModel.state.collectAsState()

    TimerLayout {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Próximo año bisiesto",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row {
                state.units.forEachIndexed { index, unit ->
                    if (index > 0) {
                        TimeSeparator()
                    }
                    TimeBlock(unit.value, unit.label)
                }
            }

        }
    }

}
