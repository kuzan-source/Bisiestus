package com.espiralsoft.bisiestus.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.espiralsoft.bisiestus.presentation.components.TimerLayout
import com.espiralsoft.bisiestus.presentation.viewModel.TimerViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = TimerViewModel(),
){
    // Obtenemos el estado del ViewModel
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Text(
                text = "Próximo año bisiesto",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        TimerLayout(units = state.units)
    }
}
