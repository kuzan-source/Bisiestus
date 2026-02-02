package com.espiralsoft.bisiestus.presentation.ui.screens

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
import com.espiralsoft.bisiestus.presentation.ui.components.TimerLayout
import com.espiralsoft.bisiestus.presentation.viewModel.CountdownViewModel

@Composable
fun TimerScreen(
    viewModel: CountdownViewModel = CountdownViewModel(),
){
    // Obtenemos el estado del ViewModel
    val state by viewModel.uiState.collectAsState()

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
        if (state.isFeb29) {
            Text(
                text = "Es 29 de feb",
                style = MaterialTheme.typography.headlineMedium
            )
        } else {
            TimerLayout(units = state)
        }
    }
}
