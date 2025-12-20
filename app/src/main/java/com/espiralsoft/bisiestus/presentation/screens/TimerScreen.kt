package com.espiralsoft.bisiestus.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.espiralsoft.bisiestus.presentation.viewModel.TimerViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = TimerViewModel(),
){
    // Obtenemos el estado del ViewModel
    val state by viewModel.state.collectAsState()

    // El Box actuará como el contenedor principal de la pantalla
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = state.timeNow.toString(),
            onValueChange = {},
            readOnly = true
        )
    }

}
